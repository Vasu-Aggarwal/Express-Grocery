package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignCouponRequest {

    @NotNull
    @NotEmpty
    private String userUuid;

    @NotNull
    @NotEmpty
    private String couponName;

}
