package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
