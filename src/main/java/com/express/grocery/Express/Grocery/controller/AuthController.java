package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.JwtRequest;
import com.express.grocery.Express.Grocery.dto.request.RefreshTokenRequest;
import com.express.grocery.Express.Grocery.dto.request.UserRegisterRequest;
import com.express.grocery.Express.Grocery.dto.response.JwtResponse;
import com.express.grocery.Express.Grocery.dto.response.UserRegisterResponse;
import com.express.grocery.Express.Grocery.entity.RefreshToken;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.security.JwtHelper;
import com.express.grocery.Express.Grocery.service.RefreshTokenService;
import com.express.grocery.Express.Grocery.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @Autowired
    private JwtHelper helper;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        this.doAuthenticate(request.getUsername(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = this.helper.generateToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getUsername());

        JwtResponse response = JwtResponse.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .token(token).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/registerUser")
    public ResponseEntity<UserRegisterResponse> registerNewUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse user = this.userService.registerUser(userRegisterRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/registerAdminUser")
    public ResponseEntity<UserRegisterResponse> registerAdminUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        UserRegisterResponse user = this.userService.registerAminUser(userRegisterRequest);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest){
        RefreshToken refreshToken = refreshTokenService.verifyRefreshToken(refreshTokenRequest.getRefreshToken());
        User user = refreshToken.getUser();
        String token = helper.generateToken(user);
        return new ResponseEntity<>(JwtResponse.builder()
                .refreshToken(refreshToken.getRefreshToken())
                .token(token)
                .build(), HttpStatus.OK);
    }

    private void doAuthenticate(String username, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(username, password);
        try {
            manager.authenticate(authentication);


        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
