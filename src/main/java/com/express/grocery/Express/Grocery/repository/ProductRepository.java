package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByProductNameContainingIgnoreCase(String productName);
}
