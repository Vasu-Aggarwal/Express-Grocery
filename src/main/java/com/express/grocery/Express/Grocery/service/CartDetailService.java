package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.request.RemoveFromCart;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;

import java.util.Map;

public interface CartDetailService {
    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);
    Map<String, Object> removeFromCart(RemoveFromCart removeFromCart);
}
