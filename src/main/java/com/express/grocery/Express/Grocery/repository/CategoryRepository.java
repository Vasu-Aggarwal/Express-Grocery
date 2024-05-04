package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
