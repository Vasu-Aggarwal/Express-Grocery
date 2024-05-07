package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCouponsResponse {

    private Integer couponId;
    private Integer discountPercent;
    private Double maxDiscount;
    private String couponName;

}
