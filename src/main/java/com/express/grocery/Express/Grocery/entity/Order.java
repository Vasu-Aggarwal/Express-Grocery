package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @CreationTimestamp
    @Column(name = "order_date")
    private LocalDateTime orderDate;
    @Column(name = "order_amount")
    private Double orderAmount;
    @Column(name = "order_status")
    private Integer orderStatus;

    @ManyToOne
    @JoinColumn(name = "ordered_by", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @OneToOne(mappedBy = "order_Id", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Invoice invoice;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "products_ordered", joinColumns = @JoinColumn(name = "order_id", referencedColumnName = "order_id"), inverseJoinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"))
    private List<Product> products = new ArrayList<>();
}
