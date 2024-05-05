package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Coupon findByCouponName(String name);
}
