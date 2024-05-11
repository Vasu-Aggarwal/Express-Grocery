package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.UserRegisterRequest;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;
import com.express.grocery.Express.Grocery.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        UserRegisterResponse user = userService.createUser(userRegisterRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

}
