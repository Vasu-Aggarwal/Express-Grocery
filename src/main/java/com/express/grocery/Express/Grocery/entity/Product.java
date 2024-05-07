package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@EntityListeners(AuditingEntityListener.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;
    @Column(name = "product_price")
    private Double productPrice;
    @Column(name = "in_stock_quantity")
    private Integer inStockQuantity;
    @Column(name = "product_name", unique = true)
    private String productName;
    @Column(name = "is_available")
    private Boolean isAvailable;
    @Column(name = "about_product")
    private String aboutProduct;
    @Column(name = "product_img")
    private String productImg;

    @CreationTimestamp
    @Column(name = "added_on", updatable = false)
    private Timestamp addedOn;

    @LastModifiedDate
    @Column(name = "modified_on")
    private Timestamp modifiedOn;

    @ManyToOne
    @JoinColumn(name = "added_by", nullable = false)
    private User addedBy;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories = new ArrayList<>();

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartDetail> cartDetails = new ArrayList<>();

}
