package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.dto.request.OrderCheckoutRequest;
import com.express.grocery.Express.Grocery.dto.request.UpdateOrderStatusRequest;
import com.express.grocery.Express.Grocery.dto.response.OrderCheckoutResponse;
import com.express.grocery.Express.Grocery.entity.*;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.repository.OrderRepository;
import com.express.grocery.Express.Grocery.repository.TransactionRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public OrderCheckoutResponse orderCheckout(OrderCheckoutRequest orderCheckoutRequest) {
        Order order = new Order();

        //find user with user uuid present in request
        User user = userRepository.findById(orderCheckoutRequest.getUser()).orElseThrow(() -> new ResourceNotFoundException(String.format("User with uuid: %s not found", orderCheckoutRequest.getUser()), 0));

        //find the cart id from user
        Cart cart = user.getCart();

        //find the cart details
        List<CartDetail> cartDetail = cart.getCartDetails();

        //if user found then set the user
        order.setUser(user);

        //add products that are being ordered
        List<Product> products = cartDetail.stream().map(CartDetail::getProduct).collect(Collectors.toList());
        order.setProducts(products);

        //Link cart to the order
        order.setCartId(cart);
        order.setOrderAmount(cart.getNetAmount());

        //Check if coupon was applied by the user in Cart
        if (cart.getCouponApplied()){
            order.setCoupon(cart.getCoupon());
        }

        return modelMapper.map(orderRepository.save(order), OrderCheckoutResponse.class);
    }

    @Override
    public Map<String, Object> updateOrderStatus(UpdateOrderStatusRequest updateOrderStatusRequest) {
        //find order
        Order order = orderRepository.findById(updateOrderStatusRequest.getOrderId()).orElseThrow(() -> new ResourceNotFoundException("Something went wrong, order not found.", 0));

        //if order is status is in transaction then add new transaction in db.
        if (updateOrderStatusRequest.getOrderStatus() == AppConstants.ORDER_IN_TRANSACT.getValue()){
            Transaction transaction = new Transaction();
            transaction.setOrder_id(order);
            transaction.setTransactionStatus(AppConstants.TRANSACTION_PENDING.getValue());
            transaction.setTransactionAmount(order.getOrderAmount());
            transaction.setTransactionMode(AppConstants.TRANSACTION_MODE_COD.getValue()); //this is dummy value because after this user have to make new transaction for proper payment.
            transactionRepository.save(transaction);

        }
        order.setOrderStatus(updateOrderStatusRequest.getOrderStatus());
        orderRepository.save(order);
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Order status updated successfully");
        response.put("status", 1);
        return response;
    }
}
