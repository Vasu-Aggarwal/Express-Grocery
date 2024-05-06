package com.express.grocery.Express.Grocery.service;


import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;

import java.util.List;

public interface CartService {

    List<AddToCartResponse> getCartDetails(String userUuid);

}
