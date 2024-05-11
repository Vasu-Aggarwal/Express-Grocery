package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.config.AppConstants;
import com.express.grocery.Express.Grocery.config.UserRole;
import com.express.grocery.Express.Grocery.dto.UserDto;
import com.express.grocery.Express.Grocery.dto.request.UserRegisterRequest;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;
import com.express.grocery.Express.Grocery.entity.Cart;
import com.express.grocery.Express.Grocery.entity.Role;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.BadApiResponse;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CartRepository;
import com.express.grocery.Express.Grocery.repository.RoleRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest) {

        try {
            User user = modelMapper.map(userRegisterRequest, User.class);
            user.setUsername(userRegisterRequest.getName().substring(0, 2) + userRegisterRequest.getMobile().toString().substring(0, 3) + UUID.randomUUID().toString().substring(0, 4));
            user.setIsCoupon(false);
            User savedUser = userRepository.save(user);

            //When user is created, create a new cart for this user to add the products
            Cart cart = new Cart();
            cart.setUser(savedUser);
            cart.setCouponApplied(false);
            cartRepository.save(cart);
            return modelMapper.map(savedUser, UserRegisterResponse.class);
        } catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Mobile or Email already exists. Please try again with different mobile or email");
        }
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

    @Override
    public UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest) {
        try {
            User user = this.modelMapper.map(userRegisterRequest, User.class);
            user.setUsername(userRegisterRequest.getName().substring(0, 2) + userRegisterRequest.getMobile().toString().substring(0, 3) + UUID.randomUUID().toString().substring(0, 4));
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setIsCoupon(false);
            User savedUser = userRepository.save(user);

            //When user is created, create a new cart for this user to add the products
            Cart cart = new Cart();
            cart.setUser(savedUser);
            cart.setCouponApplied(false);
            cartRepository.save(cart);

            //set role
            Role role = this.roleRepository.findById(UserRole.NORMAL_USER).get();
            user.getRoles().add(role);
            return this.modelMapper.map(this.userRepository.save(user), UserRegisterResponse.class);
        } catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Mobile or Email already exists. Please try again with different mobile or email");
        }
    }

    @Override
    public UserRegisterResponse registerAminUser(UserRegisterRequest userRegisterRequest) {
        try {
            User user = this.modelMapper.map(userRegisterRequest, User.class);
            user.setUsername(userRegisterRequest.getName().substring(0, 2) + userRegisterRequest.getMobile().toString().substring(0, 3) + UUID.randomUUID().toString().substring(0, 4));
            user.setPassword(this.passwordEncoder.encode(user.getPassword()));
            user.setIsCoupon(false);
            User savedUser = userRepository.save(user);

            //When user is created, create a new cart for this user to add the products
            Cart cart = new Cart();
            cart.setUser(savedUser);
            cart.setCouponApplied(false);
            cartRepository.save(cart);

            //set role
            Role role = this.roleRepository.findById(UserRole.ADMIN_USER).get();
            user.getRoles().add(role);
            return this.modelMapper.map(this.userRepository.save(user), UserRegisterResponse.class);
        } catch (DataIntegrityViolationException ex){
            throw new DataIntegrityViolationException("Mobile or Email already exists. Please try again with different mobile or email");
        }
    }
}
