package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import com.express.grocery.Express.Grocery.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody AddToCartRequest addToCartRequest){
        AddToCartResponse addToCartResponse = cartDetailService.addToCart(addToCartRequest);
        return new ResponseEntity<>(addToCartResponse, HttpStatus.CREATED);
    }

    @GetMapping("/getCartDetails/{userUuid}")
    public ResponseEntity<List<AddToCartResponse>> getCartDetails(@PathVariable String userUuid){
        List<AddToCartResponse> addToCartResponses = cartService.getCartDetails(userUuid);
        return new ResponseEntity<>(addToCartResponses, HttpStatus.CREATED);
    }

}
