package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateCouponResponse;

public interface CouponService {

    AddUpdateCouponResponse addUpdateCoupon(AddUpdateCouponRequest addUpdateCouponRequest);

}
