package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCategoryRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;

import java.util.List;

public interface CategoryService {

    //Admin can manage this
    CategoryDto addUpdateCategory(AddUpdateCategoryRequest addUpdateCategoryRequest);
    void deleteCategory(Integer category_id);
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(Integer category_id);
    List<AddUpdateProductResponse> getProductsByCategoryName(String userUuid, String categoryName);
}
