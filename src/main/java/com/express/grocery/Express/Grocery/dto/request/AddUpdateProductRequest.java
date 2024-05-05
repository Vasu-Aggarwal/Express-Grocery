package com.express.grocery.Express.Grocery.dto.request;

import com.express.grocery.Express.Grocery.entity.Category;
import com.express.grocery.Express.Grocery.entity.User;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateProductRequest {

    private Integer productId;

    @NotNull
    @Min(value = 1)
    private Double productPrice;

    @NotNull
    @Min(value = 1)
    private Integer inStockQuantity;

    @NotNull
    @NotEmpty
    private String productName;

    @NotNull
    private Boolean isAvailable;

    @NotNull
    @NotEmpty
    private String aboutProduct;
    private String productImg;

    @NotNull(message = "Added by cannot be null")
    private String addedBy;

    private List<String> categories = new ArrayList<>();
}
