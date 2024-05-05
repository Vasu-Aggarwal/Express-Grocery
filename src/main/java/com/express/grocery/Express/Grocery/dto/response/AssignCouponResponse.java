package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssignCouponResponse {

    private UserRegisterResponse userUuid;
    private AddUpdateCouponResponse couponName;
}
