package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCategoryRequest;
import com.express.grocery.Express.Grocery.entity.Category;
import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CategoryRepository;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto addUpdateCategory(AddUpdateCategoryRequest addUpdateCategoryRequest) {

        //Update the category
        if (addUpdateCategoryRequest.getCategoryId()!=null){
            Category category = categoryRepository.findById(addUpdateCategoryRequest.getCategoryId())
                    .orElseThrow(() ->
                        new ResourceNotFoundException(String.format("Category with id : %s not found", addUpdateCategoryRequest.getCategoryId()), 0)
                    );
            category.setCategoryName(addUpdateCategoryRequest.getCategoryName());
            if (!addUpdateCategoryRequest.getCoupon().isEmpty()){
                Coupon coupon = couponRepository.findByCouponName(addUpdateCategoryRequest.getCoupon()).orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon not found: %s", addUpdateCategoryRequest.getCoupon()), 0));
                if (coupon.getCouponType().equalsIgnoreCase("category")){
                    category.setCoupon(coupon);
                    category.setIsCoupon(true);
                } else{
                    throw new ResourceNotFoundException("Coupon only valid for customers", 0);
                }

            } else {
                category.setIsCoupon(false);
            }
            return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        }
        //Add the category
        else{
            Category category = modelMapper.map(addUpdateCategoryRequest, Category.class);
            if (!addUpdateCategoryRequest.getCoupon().isEmpty()){
                Coupon coupon = couponRepository.findByCouponName(addUpdateCategoryRequest.getCoupon()).orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon not found: %s", addUpdateCategoryRequest.getCoupon()), 0));
                if (coupon.getCouponType().equalsIgnoreCase("category")){
                    category.setCoupon(coupon);
                    category.setIsCoupon(true);
                } else{
                    throw new ResourceNotFoundException(String.format("Coupon only valid for %s", coupon.getCouponType()), 0);
                }
            } else {
                category.setIsCoupon(false);
            }
            return modelMapper.map(categoryRepository.save(category), CategoryDto.class);
        }
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
