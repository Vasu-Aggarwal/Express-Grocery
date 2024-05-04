package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
}
