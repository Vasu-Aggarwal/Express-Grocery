package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.entity.Category;
import com.express.grocery.Express.Grocery.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateProductResponse {

    private Integer productId;
    private Double productPrice;
    private Integer inStockQuantity;
    private String productName;
    private Boolean isAvailable;
    private String aboutProduct;
    private String productImg;
    private UserRegisterResponse addedBy;
    private Timestamp addedOn;
    private List<CategoryInfoResponse> categories;
}
