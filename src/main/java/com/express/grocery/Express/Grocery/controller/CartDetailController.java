package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        AddToCartResponse addToCartResponse = cartDetailService.addToCart(addToCartRequest);
        return new ResponseEntity<>(addToCartResponse, HttpStatus.CREATED);
    }

}
