package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TimeZoneColumn;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUpdateCouponRequest {

    private Integer couponId;

    @NotNull
    private Double maxDiscount;

    @NotNull
    @Max(value = 100)
    private Integer discountPercent;

    @NotNull
    @NotEmpty
    private String couponType;

    private LocalDateTime couponExpireDate;

    @NotNull
    @NotEmpty
    private String couponName;

    private Double minimumCartValue;

    @NotNull
    private Integer couponStatus;
}
