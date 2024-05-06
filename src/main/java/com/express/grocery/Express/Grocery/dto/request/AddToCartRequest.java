package com.express.grocery.Express.Grocery.dto.request;

import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.User;
import jakarta.persistence.*;
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

    private Integer cartId;
    private Integer productQuantity;
    private String user;
    private String coupon;
    private Integer product;
}
