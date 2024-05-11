package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApplyCouponRequest {

    @NotNull(message = "User uuid cannot be null")
    @NotEmpty
    private String userUuid;
    private Integer cartId;
    private String couponName;

}
