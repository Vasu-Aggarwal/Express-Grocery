package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.UserRegisterRequest;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;
import com.express.grocery.Express.Grocery.entity.Cart;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.repository.CartRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {
        User user = modelMapper.map(userRegisterRequest, User.class);
        user.setUsername(userRegisterRequest.getName().substring(0, 2)+userRegisterRequest.getMobile().toString().substring(0, 3)+ UUID.randomUUID().toString().substring(0, 4));
        User savedUser = userRepository.save(user);
        //When user is created, create a new cart for this user to add the products
        Cart cart = new Cart();
        cart.setUser(savedUser);
        cartRepository.save(cart);
        return modelMapper.map(savedUser, UserRegisterResponse.class);
    }

    @Override
    public UserRegisterResponse updateUser(UserRegisterRequest userRegisterRequest) {
        return null;
    }

    @Override
    public UserRegisterResponse getUserByUuid(String uuid) {
        return null;
    }

    @Override
    public UserRegisterResponse getUserByUsername(String username) {
        return null;
    }

    @Override
    public void deleteUser(String user_uuid) {

    }

    @Override
    public List<UserRegisterResponse> getAllUsers() {
        return List.of();
    }
}
