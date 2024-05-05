package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.exception.BadExcelException;
import com.express.grocery.Express.Grocery.service.ProductService;
import com.express.grocery.Express.Grocery.util.ProductExcelHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @PostMapping("/excelUploadProducts/{added_by}")
    public ResponseEntity<List<AddUpdateProductResponse>> excelUploadProducts(@RequestParam("file") MultipartFile file, @PathVariable String added_by){
        if (!ProductExcelHelper.checkExcelExtension(file)){
            throw new BadExcelException("File extension should be excel");
        }
        List<AddUpdateProductResponse> products = productService.bulkUploadProducts(file, added_by);
        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

}
