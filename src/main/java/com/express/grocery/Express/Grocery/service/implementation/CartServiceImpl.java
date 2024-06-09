package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.dto.response.CartCountResponse;
import com.express.grocery.Express.Grocery.dto.response.ListCartDetailsResponse;
import com.express.grocery.Express.Grocery.entity.*;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CartRepository;
import com.express.grocery.Express.Grocery.repository.CouponRepository;
import com.express.grocery.Express.Grocery.repository.ProductRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ListCartDetailsResponse getCartDetails(String userUuid) {
        //Find user
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id: %s", userUuid), 0));

        //Find cart according to the user
        Cart cart = cartRepository.findByUser(user);

        //Get cart details of the user
        List<AddToCartResponse> cartDetails = cart.getCartDetails().stream().map((cartDetail)-> modelMapper.map(cartDetail, AddToCartResponse.class)).collect(Collectors.toList());

        for (AddToCartResponse cartResponse: cartDetails){
            AddUpdateProductResponse productResponse = cartResponse.getProduct();
            ProductServiceImpl.productDiscountHelper(productResponse);
            cartResponse.setProductAmount(cartResponse.getProductQuantity()*productResponse.getProductPrice());
            cartResponse.setProductDiscountedAmount(cartResponse.getProductQuantity()*productResponse.getProductDiscountedPrice());
        }
        ListCartDetailsResponse listCartDetailsResponse = new ListCartDetailsResponse();

        listCartDetailsResponse.setProductDetail(cartDetails);
        listCartDetailsResponse.setTotalProducts(cartDetails.size());

        //Calculate the total amount
        double totalAmount = cartDetails.stream().mapToDouble(AddToCartResponse::getProductAmount).sum();

        //Calculate the total discount amount: category discount
        double discountAmount = cartDetails.stream().mapToDouble(AddToCartResponse::getProductDiscountedAmount).sum();
        cart.setNetAmount(discountAmount);

        listCartDetailsResponse.setBeforeDiscount(totalAmount);
        listCartDetailsResponse.setAfterDiscount(discountAmount);
        listCartDetailsResponse.setSavedAmount(totalAmount-discountAmount);

        /*
        Find the discount amount after applying coupon:
            if discountPercent of cart value >= then apply maxDiscountAmount
        */

        //If additionally user has applied any coupon then reduce the afterDiscountAmount and increase the savedAmount
        if (cart.getCouponApplied()) {
            Coupon coupon = couponRepository.findByCouponName(cart.getCoupon().getCouponName()).orElseThrow(() -> new ResourceNotFoundException((String.format("Coupon with name: %s not found", cart.getCoupon().getCouponName())), 0));

            double cartDiscountAfterCoupon = listCartDetailsResponse.getAfterDiscount() * (coupon.getDiscountPercent().doubleValue() / 100);
            //if discount amount is greater than coupon max amount then give user the coupon amount discount
            if (cartDiscountAfterCoupon >= coupon.getMaxDiscount()) {
                listCartDetailsResponse.setAfterDiscount(discountAmount - coupon.getMaxDiscount());
                cart.setNetAmount(discountAmount-coupon.getMaxDiscount());
                listCartDetailsResponse.setSavedAmount((totalAmount - discountAmount) + coupon.getMaxDiscount());
            } else {
                cart.setNetAmount(discountAmount - cartDiscountAfterCoupon);
                listCartDetailsResponse.setAfterDiscount(discountAmount - cartDiscountAfterCoupon);
                listCartDetailsResponse.setSavedAmount((totalAmount - discountAmount) + cartDiscountAfterCoupon);
            }
        }

        //Then show the cart details of that cart
        cartRepository.save(cart);
        return listCartDetailsResponse;
    }

    @Override
    public ListCartDetailsResponse getCartDetailsV2(String userUuid, Integer productId) {
        //Find user
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id: %s", userUuid), 0));

        //Find cart according to the user
        Cart cart = cartRepository.findByUser(user);

        List<AddToCartResponse> cartDetails;
        if (productId == null){
            //Get cart details of the user
            cartDetails = cart.getCartDetails().stream().map((cartDetail)-> modelMapper.map(cartDetail, AddToCartResponse.class)).collect(Collectors.toList());
        } else {
            Optional<CartDetail> cartDetail = cart.getCartDetails().stream().filter(cartdetail -> productId.equals(cartdetail.getProduct().getProductId())).findFirst();
            // or handle the case where the product is not found in the cart
            cartDetails = cartDetail.map(detail -> Collections.singletonList(modelMapper.map(detail, AddToCartResponse.class))).orElse(Collections.emptyList());
            System.out.println("Cartdetails: " + cartDetails);
        }

        for (AddToCartResponse cartResponse: cartDetails){
            AddUpdateProductResponse productResponse = cartResponse.getProduct();
            ProductServiceImpl.productDiscountHelper(productResponse);
            cartResponse.setProductAmount(cartResponse.getProductQuantity()*productResponse.getProductPrice());
            cartResponse.setProductDiscountedAmount(cartResponse.getProductQuantity()*productResponse.getProductDiscountedPrice());
        }
        ListCartDetailsResponse listCartDetailsResponse = new ListCartDetailsResponse();

        listCartDetailsResponse.setProductDetail(cartDetails);
        listCartDetailsResponse.setTotalProducts(cartDetails.size());

        //Calculate the total amount
        double totalAmount = cartDetails.stream().mapToDouble(AddToCartResponse::getProductAmount).sum();

        //Calculate the total discount amount: category discount
        double discountAmount = cartDetails.stream().mapToDouble(AddToCartResponse::getProductDiscountedAmount).sum();
        cart.setNetAmount(discountAmount);

        listCartDetailsResponse.setBeforeDiscount(totalAmount);
        listCartDetailsResponse.setAfterDiscount(discountAmount);
        listCartDetailsResponse.setSavedAmount(totalAmount-discountAmount);

        /*
        Find the discount amount after applying coupon:
            if discountPercent of cart value >= then apply maxDiscountAmount
        */

        //If additionally user has applied any coupon then reduce the afterDiscountAmount and increase the savedAmount
        if (cart.getCouponApplied()) {
            Coupon coupon = couponRepository.findByCouponName(cart.getCoupon().getCouponName()).orElseThrow(() -> new ResourceNotFoundException((String.format("Coupon with name: %s not found", cart.getCoupon().getCouponName())), 0));

            double cartDiscountAfterCoupon = listCartDetailsResponse.getAfterDiscount() * (coupon.getDiscountPercent().doubleValue() / 100);
            //if discount amount is greater than coupon max amount then give user the coupon amount discount
            if (cartDiscountAfterCoupon >= coupon.getMaxDiscount()) {
                listCartDetailsResponse.setAfterDiscount(discountAmount - coupon.getMaxDiscount());
                cart.setNetAmount(discountAmount-coupon.getMaxDiscount());
                listCartDetailsResponse.setSavedAmount((totalAmount - discountAmount) + coupon.getMaxDiscount());
            } else {
                cart.setNetAmount(discountAmount - cartDiscountAfterCoupon);
                listCartDetailsResponse.setAfterDiscount(discountAmount - cartDiscountAfterCoupon);
                listCartDetailsResponse.setSavedAmount((totalAmount - discountAmount) + cartDiscountAfterCoupon);
            }
        }

        //Then show the cart details of that cart
        cartRepository.save(cart);
        return listCartDetailsResponse;
    }

    @Override
    public CartCountResponse getCartCount(String userUuid) {
        //Find user
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException(String.format("User not found with id: %s", userUuid), 0));

        //Find cart according to the user
        Cart cart = cartRepository.findByUser(user);

        CartCountResponse cartCountResponse = new CartCountResponse();
        if (cart.getCartDetails() == null){
            cartCountResponse.setCartCount(0);
        } else {
            //Total products in cart details
            Integer cartDetailCount = cart.getCartDetails().stream().mapToInt(CartDetail::getProductQuantity).sum();
            cartCountResponse.setCartCount(cartDetailCount);
            cartCountResponse.setMessage("success");
        }
        return cartCountResponse;
    }
}
