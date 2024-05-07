package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.ApplyCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.AssignCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.*;

import java.util.List;

public interface CouponService {

    AddUpdateCouponResponse addUpdateCoupon(AddUpdateCouponRequest addUpdateCouponRequest);
    AssignCouponResponse assignCoupon(AssignCouponRequest assignCouponRequest);
    List<ListCouponsResponse> listCoupons(String userUuid);
    ApplyCouponResponse applyCoupon(ApplyCouponRequest applyCouponRequest);
}
