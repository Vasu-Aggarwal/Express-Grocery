package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.config.CouponType;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.ApplyCouponRequest;
import com.express.grocery.Express.Grocery.dto.request.AssignCouponRequest;
import com.express.grocery.Express.Grocery.dto.response.*;
import com.express.grocery.Express.Grocery.entity.Cart;
import com.express.grocery.Express.Grocery.entity.Coupon;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CartRepository;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.CouponService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
public class CouponServiceImpl implements CouponService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public AddUpdateCouponResponse addUpdateCoupon(AddUpdateCouponRequest addUpdateCouponRequest) {
        if (addUpdateCouponRequest.getCouponId()!=null){
            Coupon oldCoupon = couponRepository.findById(addUpdateCouponRequest.getCouponId())
                    .orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon with id: %s not found", addUpdateCouponRequest.getCouponId()), 0));
            oldCoupon.setCouponStatus(addUpdateCouponRequest.getCouponStatus());
            oldCoupon.setCouponName(addUpdateCouponRequest.getCouponName());
            oldCoupon.setCouponType(CouponType.valueOf(addUpdateCouponRequest.getCouponType()));
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
        Coupon coupon = couponRepository.findByCouponName(assignCouponRequest.getCouponName()).orElseThrow(()-> new ResourceNotFoundException(String.format("Coupon not found: %s", assignCouponRequest.getCouponName()), 0));
        if (coupon.getCouponType() == CouponType.CUSTOMER){
            user.setIsCoupon(true);
            user.setCoupon(coupon);
            userRepository.save(user);
        } else {
            throw new ResourceNotFoundException(String.format("Coupon only valid for %s", coupon.getCouponType()), 0);
        }

        return new AssignCouponResponse(modelMapper.map(user, UserRegisterResponse.class), modelMapper.map(coupon, AddUpdateCouponResponse.class));
    }

    @Override
    public List<ListCouponsResponse> listCoupons(String userUuid) {

        List<ListCouponsResponse> couponList = new ArrayList<>();

        //find user on which available coupons will be shown
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException("User not found", 0));
        if (!user.getIsCoupon()){
            throw new ResourceNotFoundException("No coupons available", 0);
        } else {
            if (user.getCoupon() == null)
                throw new ResourceNotFoundException("No coupons available", 0);
            //Check if coupon found or no
            Coupon coupon = couponRepository.findById(user.getCoupon().getCouponId()).orElseThrow(()-> new ResourceNotFoundException("No coupons available", 0));
            couponList.add(modelMapper.map(coupon, ListCouponsResponse.class));
        }
        return couponList;
    }

    @Override
    public ApplyCouponResponse applyCoupon(ApplyCouponRequest applyCouponRequest) {
        Coupon coupon = couponRepository.findByCouponName(applyCouponRequest.getCouponName()).orElseThrow(()-> new ResourceNotFoundException((String.format("Coupon with name: %s not found", applyCouponRequest.getCouponName())), 0));

        Cart cart = cartRepository.findById(applyCouponRequest.getCartId()).orElseThrow(()-> new BadRequestException("Bad request"));
        cart.setCouponApplied(true);
        cart.setCoupon(coupon);
        cartRepository.save(cart);
        return new ApplyCouponResponse("Coupon Applied successfully", 1);
    }
}
