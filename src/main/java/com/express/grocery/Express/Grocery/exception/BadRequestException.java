package com.express.grocery.Express.Grocery.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadRequestException extends RuntimeException{

    String message;

    public BadRequestException(String message){
        super(message);
        this.message = message;
    }

}
