package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateCouponResponse {

    private Integer couponId;
    private Double maxDiscount;
    private Integer discountPercent;
    private String couponType;
    private Timestamp couponExpireDate;
    private String couponName;
    private Double minimumCartValue;
    private Integer couponStatus;
    private Timestamp createdOn;

}
