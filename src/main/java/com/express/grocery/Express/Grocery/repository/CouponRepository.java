package com.express.grocery.Express.Grocery.repository;

import com.express.grocery.Express.Grocery.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {
    Optional<Coupon> findByCouponName(String name);
}
