package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;

public interface CartDetailService {
    AddToCartResponse addToCart(AddToCartRequest addToCartRequest);
}
