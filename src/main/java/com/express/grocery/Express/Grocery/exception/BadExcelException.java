package com.express.grocery.Express.Grocery.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BadExcelException extends RuntimeException{

    String message;

    public BadExcelException(String message){
        super("Bad excel extension");
        this.message = message;
    }

}
