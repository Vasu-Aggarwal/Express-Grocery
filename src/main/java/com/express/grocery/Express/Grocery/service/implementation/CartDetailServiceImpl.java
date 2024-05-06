package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.entity.*;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.*;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
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

        if (addToCartRequest.getCart() == null)
            throw new BadRequestException("No cart found");

        //find cart
        Cart cart = cartRepository.findById(addToCartRequest.getCart()).orElseThrow(() -> new ResourceNotFoundException(String.format("Cart not found with id: %s", addToCartRequest.getCart()), 0));


        //Find the products
        Product product = productRepository.findById(addToCartRequest.getProduct())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with product id: %s not found", addToCartRequest.getProduct()), 0));

        CartDetail cartDetail = modelMapper.map(addToCartRequest, CartDetail.class);
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        return modelMapper.map(cartDetailRepository.save(cartDetail), AddToCartResponse.class);

    }
}
