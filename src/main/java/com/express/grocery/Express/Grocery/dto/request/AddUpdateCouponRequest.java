package com.express.grocery.Express.Grocery.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.TimeZoneColumn;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

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

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime couponExpireDate;

    @NotNull
    @NotEmpty
    private String couponName;

    private Double minimumCartValue;

    @NotNull
    private Integer couponStatus;
}
