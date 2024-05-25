package com.express.grocery.Express.Grocery.controller;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.exception.BadExcelException;
import com.express.grocery.Express.Grocery.exception.BadRequestException;
import com.express.grocery.Express.Grocery.service.CategoryService;
import com.express.grocery.Express.Grocery.service.ProductService;
import com.express.grocery.Express.Grocery.util.ProductExcelHelper;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/addUpdateProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<AddUpdateProductResponse> addUpdateProduct(@RequestBody @Valid AddUpdateProductRequest addUpdateProductRequest){
        AddUpdateProductResponse product = productService.addUpdateProduct(addUpdateProductRequest);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @PostMapping("/excelUploadProducts/{added_by}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<AddUpdateProductResponse>> excelUploadProducts(@RequestParam("file") MultipartFile file, @PathVariable String added_by){
        if (!ProductExcelHelper.checkExcelExtension(file)){
            throw new BadExcelException("File extension should be excel");
        }
        List<AddUpdateProductResponse> products = productService.bulkUploadProducts(file, added_by);
        return new ResponseEntity<>(products, HttpStatus.ACCEPTED);
    }

    @GetMapping("/allProductList")
    public ResponseEntity<List<AddUpdateProductResponse>> getAllProducts(){
        List<AddUpdateProductResponse> productResponses = productService.allProductList();
        return new ResponseEntity<>(productResponses, HttpStatus.ACCEPTED);
    }

    @GetMapping("/searchProduct")
    public ResponseEntity<?> searchProduct(
            @RequestParam(value = "productName", required = false) String productName,
            @RequestParam(value = "categoryName", required = false) String categoryName){

        if (productName !=null && categoryName !=null){
            AddUpdateProductResponse productResponse = productService.getProductByName(productName);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else if (productName != null){
            AddUpdateProductResponse productResponse = productService.getProductByName(productName);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } else if (categoryName != null){
            List<AddUpdateProductResponse> products = categoryService.getProductsByCategoryName(categoryName);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else {
            throw new BadRequestException("Please pass proper parameters");
        }
    }

}
