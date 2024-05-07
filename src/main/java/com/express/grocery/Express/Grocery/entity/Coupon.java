package com.express.grocery.Express.Grocery.entity;

import com.express.grocery.Express.Grocery.config.CouponType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coupon")
@EntityListeners(AuditingEntityListener.class)
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_id")
    private Integer couponId;

    @Column(name = "max_discount")
    private Double maxDiscount;
    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent;
    @Column(name = "coupon_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CouponType couponType;
    @Column(name = "coupon_expire_date")
    private LocalDateTime couponExpireDate;
    @Column(name = "coupon_name", nullable = false, unique = true)
    private String couponName;
    @Column(name = "minimum_cart_value")
    private Double minimumCartValue;
    @Column(name = "coupon_status")
    private Integer couponStatus;

    @CreationTimestamp
    @Column(name = "created_on", updatable = false)
    private Timestamp createdOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    //Coupon to user
//    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "user_coupon",
//            joinColumns = @JoinColumn(name = "coupon_id", referencedColumnName = "coupon_id"),
//            inverseJoinColumns = @JoinColumn(name = "uuid", referencedColumnName = "user_uuid")
//    )
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "coupon", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cart> carts = new ArrayList<>();

}
