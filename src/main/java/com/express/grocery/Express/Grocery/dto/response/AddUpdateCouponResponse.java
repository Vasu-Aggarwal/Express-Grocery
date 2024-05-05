package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateCouponResponse {

    private Integer couponId;
    private Double maxDiscount;
    private Integer discountPercent;
    private String couponType;
    private LocalDateTime couponExpireDate;
    private String couponName;
    private Double minimumCartValue;
    private Integer couponStatus;
    private Timestamp createdOn;

}
