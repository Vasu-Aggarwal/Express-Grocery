package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.OrderCheckoutRequest;
import com.express.grocery.Express.Grocery.dto.request.UpdateOrderStatusRequest;
import com.express.grocery.Express.Grocery.dto.response.OrderCheckoutResponse;
import com.express.grocery.Express.Grocery.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/checkout")
    public ResponseEntity<OrderCheckoutResponse> orderCheckout(@RequestBody @Valid OrderCheckoutRequest orderCheckoutRequest){
        OrderCheckoutResponse orderCheckoutResponse = orderService.orderCheckout(orderCheckoutRequest);
        return new ResponseEntity<>(orderCheckoutResponse, HttpStatus.ACCEPTED);
    }

    @PutMapping("/updateOrderStatus")
    public ResponseEntity<Map<String, Object>> updateOrderStatus(@RequestBody @Valid UpdateOrderStatusRequest updateOrderStatusRequest){
        Map<String, Object> response = orderService.updateOrderStatus(updateOrderStatusRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
