package com.express.grocery.Express.Grocery.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer product_id;

    private Double product_price;

    private Integer product_stock;

    private String product_name;

    private Boolean is_available;

    @ManyToOne
    @JoinColumn(name = "added_by")
    private User added_by;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id", referencedColumnName = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
    private List<Category> categories = new ArrayList<>();

}
