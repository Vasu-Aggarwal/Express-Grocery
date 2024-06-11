package com.express.grocery.Express.Grocery.service;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.dto.response.AllProductListResponse;
import com.express.grocery.Express.Grocery.entity.Product;
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

    //Get product by Id
    AddUpdateProductResponse getProductById(Integer product_id);

    //Apis for normal user

    //Get all the products
    AllProductListResponse allProductList(String userUuid, Integer pageNumber, Integer pageSize, String sortBy);

    //Get product by Name
    AddUpdateProductResponse getProductByName(String productName);
}
