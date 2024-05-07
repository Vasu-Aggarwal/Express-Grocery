package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCartDetailsResponse;
import com.express.grocery.Express.Grocery.entity.*;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CartRepository;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.repository.ProductRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ListCartDetailsResponse getCartDetails(String userUuid) {
        //Find user
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id: %s", userUuid), 0));

        //Find cart according to the user
        Cart cart = cartRepository.findByUser(user);

        //Get cart details of the user
        List<AddToCartResponse> cartDetails = cart.getCartDetails().stream().map((cartDetail)-> modelMapper.map(cartDetail, AddToCartResponse.class)).collect(Collectors.toList());

        for (AddToCartResponse cartResponse: cartDetails){
            AddUpdateProductResponse productResponse = cartResponse.getProduct();
            ProductServiceImpl.productDiscountHelper(productResponse);
            cartResponse.setProductAmount(cartResponse.getProductQuantity()*productResponse.getProductPrice());
            cartResponse.setProductDiscountedAmount(cartResponse.getProductQuantity()*productResponse.getProductDiscountedPrice());
        }
        ListCartDetailsResponse listCartDetailsResponse = new ListCartDetailsResponse();

        listCartDetailsResponse.setProductDetail(cartDetails);
        listCartDetailsResponse.setTotalProducts(cartDetails.size());

        //Calculate the total amount
        double totalAmount = cartDetails.stream().mapToDouble(cartDetail -> cartDetail.getProductAmount()).sum();

        //Calculate the total discount amount
        double discountAmount = cartDetails.stream().mapToDouble(cartDetail -> cartDetail.getProductDiscountedAmount()).sum();

        listCartDetailsResponse.setBeforeDiscount(totalAmount);
        listCartDetailsResponse.setAfterDiscount(discountAmount);
        listCartDetailsResponse.setSavedAmount(totalAmount-discountAmount);

        //Then show the cart details of that cart
        return listCartDetailsResponse;
    }
}
