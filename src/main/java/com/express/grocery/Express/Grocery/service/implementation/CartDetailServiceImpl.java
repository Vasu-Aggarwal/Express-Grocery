package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.*;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) {

        //find user
        User user = userRepository.findById(addToCartRequest.getUser()).orElseThrow(() -> new ResourceNotFoundException(String.format("User not found with uuid: %s", addToCartRequest.getUser()), 0));

        //find coupon
        if (addToCartRequest.getCoupon() !=null){
            Coupon coupon = couponRepository.findByCouponName(addToCartRequest.getCoupon()).orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon not found with name: %s", addToCartRequest.getCoupon()), 0));
        }

        //Find the products
//        List<Product> products = addToCartRequest.getProducts().stream()
//                .map((product) -> productRepository.findById(product)
//                        .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with product id: %s not found", product), 0)))
//                .collect(Collectors.toList());
//
//        AddToCartResponse cartResponse =
    }
}
