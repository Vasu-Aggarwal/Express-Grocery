package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.UserRegisterRequest;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;

import java.util.List;

public interface UserService {

    //Admin Functionalities

    UserRegisterResponse createUser(UserRegisterRequest userRegisterRequest);
    UserRegisterResponse updateUser(UserRegisterRequest userRegisterRequest);
    UserRegisterResponse getUserByUuid(String uuid);
    UserRegisterResponse getUserByUsername(String username);
    void deleteUser(String user_uuid);
    List<UserRegisterResponse> getAllUsers();

    UserRegisterResponse registerUser(UserRegisterRequest userRegisterRequest);
    UserRegisterResponse registerAminUser(UserRegisterRequest userRegisterRequest);
}
