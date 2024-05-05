package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/addUpdateProduct")
    public ResponseEntity<AddUpdateProductResponse> addUpdateProduct(@RequestBody @Valid AddUpdateProductRequest addUpdateProductRequest){
        AddUpdateProductResponse product = productService.addUpdateProduct(addUpdateProductRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

}
