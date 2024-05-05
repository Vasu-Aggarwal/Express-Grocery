package com.express.grocery.Express.Grocery.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResourceNotFoundException extends RuntimeException{

    String message;
    Integer status;

    public ResourceNotFoundException(String message, Integer status){
        super(message);
        this.message = message;
        this.status = status;
    }
}
