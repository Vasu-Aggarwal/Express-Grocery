package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddToCartRequest;
import com.express.grocery.Express.Grocery.dto.request.RemoveFromCart;
import com.express.grocery.Express.Grocery.dto.response.AddToCartResponse;
import com.express.grocery.Express.Grocery.entity.*;
import com.express.grocery.Express.Grocery.exception.BadApiResponse;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.*;
import com.express.grocery.Express.Grocery.service.CartDetailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CartDetailServiceImpl implements CartDetailService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CartDetailRepository cartDetailRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddToCartResponse addToCart(AddToCartRequest addToCartRequest) {

        if (addToCartRequest.getCart() == null)
            throw new BadRequestException("No cart found");

        //find cart
        Cart cart = cartRepository.findById(addToCartRequest.getCart()).orElseThrow(() -> new ResourceNotFoundException(String.format("Cart not found with id: %s", addToCartRequest.getCart()), 0));


        //Find the products
        Product product = productRepository.findById(addToCartRequest.getProduct())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with product id: %s not found", addToCartRequest.getProduct()), 0));

        CartDetail cartDetail = new CartDetail();
        cartDetail.setCart(cart);
        cartDetail.setProduct(product);
        cartDetail.setProductQuantity(addToCartRequest.getProductQuantity());
        AddToCartResponse cartResponse = modelMapper.map(cartDetailRepository.save(cartDetail), AddToCartResponse.class);
        ProductServiceImpl.productDiscountHelper(cartResponse.getProduct());
        cartResponse.setProductDiscountedAmount(cartResponse.getProduct().getProductDiscountedPrice()*cartResponse.getProductQuantity());
        cartResponse.setProductAmount(cartResponse.getProduct().getProductPrice()*cartResponse.getProductQuantity());
        return cartResponse;
    }

    @Transactional
    @Override
    public Map<String, Object> removeFromCart(RemoveFromCart removeFromCart) {

        //check if cart details belong to the user
        User user = userRepository.findById(removeFromCart.getUserUuid()).orElseThrow(() -> new ResourceNotFoundException("User not found. Something went Wrong", 0));

        List<Integer> cartDetailIds = user.getCart().getCartDetails().stream().map((CartDetail::getCartDetailId)).collect(Collectors.toList());

        if (!cartDetailIds.contains(removeFromCart.getCartDetailId())){
            throw new BadRequestException("Cart details not found. Something went wrong");
        }

        CartDetail cartDetail = cartDetailRepository.findById(removeFromCart.getCartDetailId()).orElseThrow(() -> new ResourceNotFoundException("Cart Details not found", 0));

        //if product quantity is equal to total quantity in cart then remove the product
        if (cartDetail.getProductQuantity() == 1){
            cartDetailRepository.deleteByPid(cartDetail.getCartDetailId());
        } else {
            cartDetail.setProductQuantity(cartDetail.getProductQuantity()-1);
            cartDetailRepository.save(cartDetail);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product removed successfully");
        response.put("status", 1);

        return response;
    }
}
