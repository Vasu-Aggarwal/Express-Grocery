package com.express.grocery.Express.Grocery.service;


import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.dto.response.CartCountResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCartDetailsResponse;

import java.util.List;

public interface CartService {

    ListCartDetailsResponse getCartDetails(String userUuid);
    ListCartDetailsResponse getCartDetailsV2(String userUuid, Integer productId);
    CartCountResponse getCartCount(String userUuid);
}
