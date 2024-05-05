package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.AssignCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateCouponResponse;
import com.express.grocery.Express.Grocery.dto.response.AssignCouponResponse;
import com.express.grocery.Express.Grocery.service.CouponService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/coupon")
public class CouponController {

    @Autowired
    private CouponService couponService;

    @PostMapping("/addUpdateCoupon")
    public ResponseEntity<AddUpdateCouponResponse> addUpdateCoupon(@RequestBody @Valid AddUpdateCouponRequest addUpdateCouponRequest){
        AddUpdateCouponResponse coupon = couponService.addUpdateCoupon(addUpdateCouponRequest);
        return new ResponseEntity<>(coupon, HttpStatus.CREATED);
    }

    @PostMapping("/assignCoupon")
    public ResponseEntity<AssignCouponResponse> assignCoupon(@RequestBody @Valid AssignCouponRequest assignCouponRequest){
        AssignCouponResponse response = couponService.assignCoupon(assignCouponRequest);
        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }

}
