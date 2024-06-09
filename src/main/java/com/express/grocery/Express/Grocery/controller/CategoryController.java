package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCategoryRequest;
import com.express.grocery.Express.Grocery.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addUpdateCategory")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addUpdateCategory(@RequestBody @Valid AddUpdateCategoryRequest addUpdateCategoryRequest){
        CategoryDto categoryDto = categoryService.addUpdateCategory(addUpdateCategoryRequest);
        return new ResponseEntity<>(categoryDto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/getCategoryList")
    public ResponseEntity<List<CategoryDto>> getAllCategory(){
        List<CategoryDto> categoryDtos = categoryService.getAllCategories();
        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

}
