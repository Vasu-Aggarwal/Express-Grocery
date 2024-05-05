package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    Category findByCategoryName(String category_name);
    List<Category> findAllByCategoryNameIn(List<String> category_name);
}
