package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "category")
@EntityListeners(AuditingEntityListener.class)
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Integer categoryId;
    @Column(name = "category_name", nullable = false, unique = true)
    private String categoryName;
    @Column(name = "is_coupon")
    private Boolean isCoupon;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> products = new ArrayList<>();
    //category coupons;

    @ManyToOne
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

}
