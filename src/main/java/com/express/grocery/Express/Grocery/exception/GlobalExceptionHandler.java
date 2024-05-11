package com.express.grocery.Express.Grocery.exception;

import org.apache.coyote.Response;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleMethodArgsNotValidException(MethodArgumentNotValidException ex){
        Map<String, Object> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            resp.put(fieldName, message);
            resp.put("status", 0);
        });

        return new ResponseEntity<Map<String, Object>>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleResourceNotFoundException(ResourceNotFoundException ex){
        Map<String, Object> resp = new HashMap<>();
        resp.put("message", ex.message);
        resp.put("status", ex.status);
        return new ResponseEntity<>(resp, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadExcelException.class)
    public ResponseEntity<BadApiResponse> badExcelException(BadExcelException ex){
        String message = ex.getMessage();
        BadApiResponse badApiResponse = new BadApiResponse(message, 0);
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadApiResponse> badRequestException(BadRequestException ex){
        String message = ex.getMessage();
        BadApiResponse badApiResponse = new BadApiResponse(message, 0);
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<BadApiResponse> duplicateEntry(DataIntegrityViolationException ex){
        String message = ex.getMessage();
        BadApiResponse badApiResponse = new BadApiResponse(message, 0);
        return new ResponseEntity<>(badApiResponse, HttpStatus.BAD_REQUEST);
    }

}
