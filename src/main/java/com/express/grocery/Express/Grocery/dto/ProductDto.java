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

    private Integer product_id;
    private Double product_price;
    private Integer in_stock_quantity;
    private String product_name;
    private Boolean is_available;
    private String about_product;
    private String product_img;
    private Timestamp added_on;
    private Timestamp modified_on;
    private User added_by;
}
