package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartResponse {

    private Integer cartDetailId;
    private Integer productQuantity;
    private CartResponse cart;
    private LocalDateTime createdOn;
    private AddUpdateProductResponse product;
    private Double productAmount;
    private Double productDiscountedAmount;
}
