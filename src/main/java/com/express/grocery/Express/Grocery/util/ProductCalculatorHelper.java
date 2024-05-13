package com.express.grocery.Express.Grocery.util;

import com.express.grocery.Express.Grocery.entity.Category;
import com.express.grocery.Express.Grocery.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCalculatorHelper {

    private Double productDiscountedAmount;
    private Integer discountOnCategory;
    private Double savedAmount;

    public void findProductDiscount(Product product){
        List<Category> applicableDiscountedCategories = product.getCategories().stream().filter(Category::getIsCoupon).collect(Collectors.toList());
        Optional<Category> maxDiscountCategory = applicableDiscountedCategories.stream().max(Comparator.comparingDouble(cat-> cat.getCoupon().getDiscountPercent()));
        maxDiscountCategory.ifPresentOrElse(category -> {
            double maxDiscount = product.getProductPrice() * ((double) (category.getCoupon().getDiscountPercent()) /100);
            double discountAmount = 0;
            if (maxDiscount >= category.getCoupon().getMaxDiscount()){
                discountAmount = product.getProductPrice() - category.getCoupon().getMaxDiscount();
            } else {
                discountAmount = product.getProductPrice() - (product.getProductPrice() * ((double) (category.getCoupon().getDiscountPercent()) /100));
            }
            setSavedAmount(product.getProductPrice() - discountAmount);
            setDiscountOnCategory(category.getCoupon().getDiscountPercent());
            setProductDiscountedAmount(discountAmount);
        }, () -> {
            setSavedAmount(0.00);
            setDiscountOnCategory(0);
            setProductDiscountedAmount(product.getProductPrice());
        });
    }
}
