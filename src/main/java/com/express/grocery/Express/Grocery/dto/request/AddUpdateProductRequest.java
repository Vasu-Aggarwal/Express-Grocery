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

    private Integer product_id;

    @NotNull
    @Min(value = 1)
    private Double product_price;

    @NotNull
    @Min(value = 1)
    private Integer in_stock_quantity;

    @NotNull
    @NotEmpty
    private String product_name;

    @NotNull
    private Boolean is_available;

    @NotNull
    @NotEmpty
    private String about_product;
    private String product_img;

    @NotNull(message = "Added by cannot be null")
    private String added_by;

    private List<Category> categories = new ArrayList<>();
}
