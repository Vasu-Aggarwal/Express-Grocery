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

    public void findProductDiscount(Product product){
        List<Category> applicableDiscountedCategories = product.getCategories().stream().filter(Category::getIsCoupon).collect(Collectors.toList());
        Optional<Category> maxDiscountCategory = applicableDiscountedCategories.stream().max(Comparator.comparingDouble(cat-> cat.getCoupon().getDiscountPercent()));
        maxDiscountCategory.ifPresentOrElse(category -> {
            double discountAmount = product.getProductPrice() - (product.getProductPrice() * ((double) (category.getCoupon().getDiscountPercent()) /100));
            setDiscountOnCategory(category.getCoupon().getDiscountPercent());
            setProductDiscountedAmount(discountAmount);
        }, () -> {
            setDiscountOnCategory(0);
            setProductDiscountedAmount(product.getProductPrice());
        });
    }
}
