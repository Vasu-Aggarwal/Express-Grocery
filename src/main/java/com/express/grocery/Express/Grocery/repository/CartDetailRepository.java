package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.CartDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartDetailRepository extends JpaRepository<CartDetail, Integer> {
}
