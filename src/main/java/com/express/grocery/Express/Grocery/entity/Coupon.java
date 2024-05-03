package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer coupon_id;

    private Double max_discount;
    private Integer discount_percent;
    private String coupon_type;
    private Timestamp coupon_expire_date;
    private String coupon_name;
    private Double minimum_cart_value;

}
