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

import javax.swing.text.html.Option;
import java.util.*;
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

        //find user
        User user = userRepository.findById(addToCartRequest.getUserUuid()).orElseThrow(() -> new ResourceNotFoundException("User not found", 0));

        //find cart
        Cart cart = cartRepository.findById(user.getCart().getCartId()).orElseThrow(() -> new ResourceNotFoundException(String.format("Cart not found with id: %s", user.getCart().getCartId()), 0));


        //Find the products
        Product product = productRepository.findById(addToCartRequest.getProduct())
                .orElseThrow(() -> new ResourceNotFoundException(String.format("Product with product id: %s not found", addToCartRequest.getProduct()), 0));

        // Check if the product already exists in the cart
        Optional<CartDetail> existingCartDetail = cart.getCartDetails().stream()
                .filter(cartDetail -> product.getProductId().equals(cartDetail.getProduct().getProductId()))
                .findFirst();
        CartDetail cartDetail;
        if (existingCartDetail.isPresent()) {
            // If the product exists, update the quantity
            cartDetail = existingCartDetail.get();
            cartDetail.setProductQuantity(cartDetail.getProductQuantity() + addToCartRequest.getProductQuantity());
        } else {
            cartDetail = new CartDetail();
            cartDetail.setCart(cart);
            cartDetail.setProduct(product);
            cartDetail.setProductQuantity(addToCartRequest.getProductQuantity());
        }
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

        //Find the cartDetail which contains the product mentioned in the removeCart
        Optional<CartDetail> cartDetail = user.getCart().getCartDetails().stream().filter(
                (cart -> removeFromCart.getProduct().equals(cart.getProduct().getProductId())
                )).findFirst();

        //if product quantity is equal to total quantity in cart then remove the product
        if (cartDetail.isPresent()){
            if (cartDetail.get().getProductQuantity() == 1){
                cartDetailRepository.deleteByCartDetailId(cartDetail.get().getCartDetailId());
//            cartDetailRepository.delete(cartDetail);
            } else {
                cartDetail.get().setProductQuantity(cartDetail.get().getProductQuantity()-1);
                cartDetailRepository.save(cartDetail.get());
            }
        } else {
            throw new ResourceNotFoundException("Something went wrong", 0);
        }
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Product removed successfully");
        response.put("status", 1);

        return response;
    }
}
