package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.Product;
import com.express.grocery.Express.Grocery.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartResponse {

    private Integer cartId;
    private Boolean couponApplied;
    private Integer productQuantity;
    private UserRegisterResponse user;
    private AddUpdateCouponResponse coupon;
    private LocalDateTime createdOn;
    private List<AddUpdateProductResponse> products = new ArrayList<>();
}
