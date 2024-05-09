package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.dto.request.OrderCheckoutRequest;
import com.express.grocery.Express.Grocery.dto.request.UpdateOrderStatusRequest;
import com.express.grocery.Express.Grocery.dto.response.OrderCheckoutResponse;
import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.Order;
import com.express.grocery.Express.Grocery.entity.Transaction;
import com.express.grocery.Express.Grocery.entity.User;
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
import java.util.Map;
import java.util.Objects;

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
        Order order = modelMapper.map(orderCheckoutRequest, Order.class);

        //find user with user uuid present in request
        User user = userRepository.findById(orderCheckoutRequest.getUser()).orElseThrow(() -> new ResourceNotFoundException(String.format("User with uuid: %s not found", orderCheckoutRequest.getUser()), 0));

        //if user found then set the user
        order.setUser(user);

        //check if coupon is null or not
        Order newOrder;
        if (orderCheckoutRequest.getCoupon() == null || orderCheckoutRequest.getCoupon().isEmpty() || orderCheckoutRequest.getCoupon().isBlank()){
            newOrder = orderRepository.save(order);
        } else {
            Coupon coupon = couponRepository.findByCouponName(orderCheckoutRequest.getCoupon()).orElseThrow(() -> new ResourceNotFoundException(String.format("Coupon with name: %s not found", orderCheckoutRequest.getCoupon()), 0));

            order.setCoupon(coupon);
            newOrder = orderRepository.save(order);
        }

        return modelMapper.map(newOrder, OrderCheckoutResponse.class);
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
