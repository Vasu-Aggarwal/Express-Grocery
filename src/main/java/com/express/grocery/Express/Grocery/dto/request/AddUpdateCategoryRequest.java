package com.express.grocery.Express.Grocery.dto.request;

import com.express.grocery.Express.Grocery.entity.Coupon;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUpdateCategoryRequest {

    @NotNull(message = "User uuid cannot be null")
    @NotEmpty
    private String userUuid;

    private Integer categoryId;

    @NotEmpty
    @NotNull
    private String categoryName;
    private String coupon;
}
