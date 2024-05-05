package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.AssignCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateCouponResponse;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.dto.response.AssignCouponResponse;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;
import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.CouponService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public AddUpdateCouponResponse addUpdateCoupon(AddUpdateCouponRequest addUpdateCouponRequest) {
        if (addUpdateCouponRequest.getCouponId()!=null){
            Coupon oldCoupon = couponRepository.findById(addUpdateCouponRequest.getCouponId())
                    .orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon with id: %s not found", addUpdateCouponRequest.getCouponId()), 0));
            oldCoupon.setCouponStatus(addUpdateCouponRequest.getCouponStatus());
            oldCoupon.setCouponName(addUpdateCouponRequest.getCouponName());
            oldCoupon.setCouponType(addUpdateCouponRequest.getCouponType());
            oldCoupon.setCouponExpireDate(addUpdateCouponRequest.getCouponExpireDate());
            oldCoupon.setDiscountPercent(addUpdateCouponRequest.getDiscountPercent());
            oldCoupon.setMaxDiscount(addUpdateCouponRequest.getMaxDiscount());
            oldCoupon.setMinimumCartValue(addUpdateCouponRequest.getMinimumCartValue());
            return modelMapper.map(couponRepository.save(oldCoupon), AddUpdateCouponResponse.class);
        } else {
            Coupon coupon = modelMapper.map(addUpdateCouponRequest, Coupon.class);
            coupon.setCouponStatus(AppConstants.ACTIVE_COUPON.getValue());
            return modelMapper.map(couponRepository.save(coupon), AddUpdateCouponResponse.class);
        }
    }

    @Override
    public AssignCouponResponse assignCoupon(AssignCouponRequest assignCouponRequest) {
        User user = userRepository.findById(assignCouponRequest.getUserUuid())
                .orElseThrow( () ->
                        new ResourceNotFoundException(String.format("User with uuid : %s not found", assignCouponRequest.getUserUuid()), 0)
            );
        Coupon coupon = couponRepository.findByCouponName(assignCouponRequest.getCouponName()).orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon not found: %s", assignCouponRequest.getCouponName()), 0));;
        user.setIsCoupon(true);
        user.setCoupon(coupon);
        userRepository.save(user);
        return new AssignCouponResponse(modelMapper.map(user, UserRegisterResponse.class), modelMapper.map(coupon, AddUpdateCouponResponse.class));
    }
}
