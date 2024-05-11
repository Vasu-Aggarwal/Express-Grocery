package com.express.grocery.Express.Grocery.dto.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class JwtRequest {

    private String username;
    private String password;

}
