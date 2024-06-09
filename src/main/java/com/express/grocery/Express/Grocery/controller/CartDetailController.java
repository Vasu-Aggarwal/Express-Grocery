package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.request.RemoveFromCart;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.dto.response.CartCountResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCartDetailsResponse;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import com.express.grocery.Express.Grocery.service.CartService;
import jakarta.annotation.PreDestroy;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/cart")
public class CartDetailController {

    @Autowired
    private CartDetailService cartDetailService;

    @Autowired
    private CartService cartService;

    @PostMapping("/addToCart")
    public ResponseEntity<AddToCartResponse> addToCart(@RequestBody @Valid AddToCartRequest addToCartRequest){
        AddToCartResponse addToCartResponse = cartDetailService.addToCart(addToCartRequest);
        return new ResponseEntity<>(addToCartResponse, HttpStatus.CREATED);
    }

    @PostMapping("/removeFromCart")
    public ResponseEntity<Map<String, Object>> removeFromCart(@RequestBody @Valid RemoveFromCart removeFromCart){
        Map<String, Object> response = cartDetailService.removeFromCart(removeFromCart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getCartDetails/{userUuid}")
    public ResponseEntity<ListCartDetailsResponse> getCartDetails(
            @PathVariable String userUuid,
            @RequestParam(required = false) Integer productId,
            @RequestParam(required = false, defaultValue = "2") String v
    ){
        if ("2".equals(v)){
            // Call the version 2 service method
            ListCartDetailsResponse listCartDetailsResponse = cartService.getCartDetailsV2(userUuid, productId);
            return new ResponseEntity<>(listCartDetailsResponse, HttpStatus.OK);
        } else {
            ListCartDetailsResponse listCartDetailsResponse = cartService.getCartDetails(userUuid);
            return new ResponseEntity<>(listCartDetailsResponse, HttpStatus.CREATED);
        }
    }

    @GetMapping("/getCartCount/{userUuid}")
    public ResponseEntity<CartCountResponse> getCartCount(@PathVariable String userUuid){
        CartCountResponse cartCountResponse = cartService.getCartCount(userUuid);
        return new ResponseEntity<>(cartCountResponse, HttpStatus.OK);
    }

}
