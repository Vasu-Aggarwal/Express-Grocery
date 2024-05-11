package com.express.grocery.Express.Grocery.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Normalized;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {

    @NotNull
    @NotEmpty
    @Email(message = "Invalid Email")
    private String email;

    @NotNull
    @Min(value = 10)
    private Long mobile;

    @NotNull
    @NotEmpty
    @Size(min = 4, message = "Invalid password")
    private String password;

    @NotEmpty
    @NotNull
    private String name;
    private Boolean isCoupon;

    private String role;
}
