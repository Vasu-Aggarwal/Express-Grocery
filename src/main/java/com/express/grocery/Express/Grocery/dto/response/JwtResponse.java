package com.express.grocery.Express.Grocery.dto.response;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class JwtResponse {

    private String token;

}
