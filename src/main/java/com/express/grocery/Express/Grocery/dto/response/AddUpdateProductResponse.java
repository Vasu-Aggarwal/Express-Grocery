package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateProductResponse {

    private Integer product_id;
    private Double product_price;
    private Integer in_stock_quantity;
    private String product_name;
    private Boolean is_available;
    private String about_product;
    private String product_img;
    private UserRegisterResponse added_by;
    private Timestamp added_on;
}
