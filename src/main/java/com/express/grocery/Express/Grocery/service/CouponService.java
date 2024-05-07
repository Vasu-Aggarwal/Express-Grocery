package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.AssignCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateCouponResponse;
import com.express.grocery.Express.Grocery.dto.response.AssignCouponResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCartDetailsResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCouponsResponse;

import java.util.List;

public interface CouponService {

    AddUpdateCouponResponse addUpdateCoupon(AddUpdateCouponRequest addUpdateCouponRequest);
    AssignCouponResponse assignCoupon(AssignCouponRequest assignCouponRequest);
    List<ListCouponsResponse> listCoupons(String userUuid);
}
