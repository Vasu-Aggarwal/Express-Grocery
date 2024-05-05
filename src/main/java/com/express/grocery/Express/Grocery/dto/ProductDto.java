package com.express.grocery.Express.Grocery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.express.grocery.Express.Grocery.entity.User;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer productId;
    private Double productPrice;
    private Integer inStockQuantity;
    private String productName;
    private Boolean isAvailable;
    private String aboutProduct;
    private String productImg;
    private Timestamp addedOn;
    private Timestamp modifiedOn;
    private User addedBy;
}
