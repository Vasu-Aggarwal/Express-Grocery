package com.express.grocery.Express.Grocery.dto.response;

import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderCheckoutResponse {

    private Integer orderId;
    private LocalDateTime orderDate;
    private Double orderAmount;
    private Integer orderStatus;
    private Coupon coupon;
//    private Invoice invoice;
    private List<Product> products = new ArrayList<>();

}
