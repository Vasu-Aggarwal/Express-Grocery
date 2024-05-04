package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserRegisterRequest {

    @NotNull
    @NotEmpty
    @Email(message = "Invalid Email")
    private String email;

    @NotEmpty
    @NotNull
    private Integer mobile;

    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Invalid password")
    private String password;

    @NotEmpty
    @NotNull
    private String name;
    private Boolean is_coupon;
}
