package com.express.grocery.Express.Grocery.dto;

import com.express.grocery.Express.Grocery.entity.Order;
import com.express.grocery.Express.Grocery.entity.Product;
import com.express.grocery.Express.Grocery.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String userUuid;
    private String username;
    private String email;
    private Integer mobile;
    private String password;
    private String name;
    private Timestamp createdOn;
    private Boolean isCoupon;
}
