package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
