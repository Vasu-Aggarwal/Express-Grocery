package com.express.grocery.Express.Grocery.dto;

import com.express.grocery.Express.Grocery.config.CouponType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {

    private Integer couponId;
    private Double maxDiscount;
    private Integer discountPercent;
    private CouponType couponType;
    private LocalDateTime couponExpireDate;
    private String couponName;
    private Double minimumCartValue;
    private Integer couponStatus;

}
