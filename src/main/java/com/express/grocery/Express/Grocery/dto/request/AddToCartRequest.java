package com.express.grocery.Express.Grocery.dto.request;

import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddToCartRequest {

    @NotNull(message = "User uuid cannot be null")
    @NotEmpty
    private String userUuid;

    @NotNull(message = "Product Quantity cannot be null")
    private Integer productQuantity;

    @NotNull(message = "product id cannot be null")
    private Integer product;

    @NotNull
    private Integer cart;
}
