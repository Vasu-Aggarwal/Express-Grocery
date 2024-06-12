package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCartDetailsResponse {

    private List<AddToCartResponse> productDetail;
    private long totalProducts;
    private Integer totalInCartItems;
    private Double beforeDiscount;
    private Double afterDiscount;
    private Double savedAmount;
}
