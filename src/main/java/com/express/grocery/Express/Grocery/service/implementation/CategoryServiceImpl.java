package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCategoryRequest;
import com.express.grocery.Express.Grocery.service.CategoryService;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDto addUpdateCategory(AddUpdateCategoryRequest addUpdateCategoryRequest) {
        return null;
    }

    @Override
    public void deleteCategory(Integer category_id) {

    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return List.of();
    }

    @Override
    public CategoryDto getCategoryById(Integer category_id) {
        return null;
    }
}
