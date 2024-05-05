package com.express.grocery.Express.Grocery.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BadApiResponse {
    String message;
    Integer status;
}
