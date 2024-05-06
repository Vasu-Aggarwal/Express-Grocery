package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Cart;
import com.express.grocery.Express.Grocery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUser(User user);
}
