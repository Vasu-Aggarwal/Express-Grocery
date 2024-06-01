package com.express.grocery.Express.Grocery.exception;

public class BadCredentialException extends RuntimeException{
    public BadCredentialException(){
        super("Incorrect Password");
    }
}
