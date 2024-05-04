package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupon")
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer coupon_id;

    private Double max_discount;
    private Integer discount_percent;
    private String coupon_type;
    private Timestamp coupon_expire_date;
    private String coupon_name;
    private Double minimum_cart_value;
    private Integer coupon_status;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Timestamp created_on;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Timestamp modified_on;

    //Coupon to user
//    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany
    @JoinTable(
            name = "user_coupon",
            joinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id"),
            inverseJoinColumns = @JoinColumn(name = "uuid", referencedColumnName = "user_uuid")
    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

}
