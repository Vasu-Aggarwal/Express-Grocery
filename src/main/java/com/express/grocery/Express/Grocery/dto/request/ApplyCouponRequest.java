package com.express.grocery.Express.Grocery.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCouponRequest {

    private Integer cartId;
    private String couponName;

}
