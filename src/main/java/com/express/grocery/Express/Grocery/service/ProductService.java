package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    //Only Admin can manage the products

    //Add or update a single product
    AddUpdateProductResponse addUpdateProduct(AddUpdateProductRequest addUpdateProductRequest);

    //Delete the product
    void deleteProduct(Integer product_id);

    //Bulk upload products from excel
    List<AddUpdateProductResponse> bulkUploadProducts(MultipartFile file, String added_by);

    //Get all the products
    List<AddUpdateProductResponse> getAllProducts();

    //Get product by Id
    AddUpdateProductResponse getProductById(Integer product_id);
}
