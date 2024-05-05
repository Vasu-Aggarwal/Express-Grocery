package com.express.grocery.Express.Grocery.dto;

import com.express.grocery.Express.Grocery.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private Integer categoryId;
    private String categoryName;
    private Boolean isCoupon;
    private Coupon coupon;
}
