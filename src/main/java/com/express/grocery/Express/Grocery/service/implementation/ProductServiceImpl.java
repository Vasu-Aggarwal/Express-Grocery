package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.entity.Category;
import com.express.grocery.Express.Grocery.entity.Product;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.CategoryRepository;
import com.express.grocery.Express.Grocery.repository.ProductRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.ProductService;
import com.express.grocery.Express.Grocery.util.ProductExcelHelper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    @Transactional
    public AddUpdateProductResponse addUpdateProduct(AddUpdateProductRequest addUpdateProductRequest) {
        Product product = modelMapper.map(addUpdateProductRequest, Product.class);
        //Update product
        if (addUpdateProductRequest.getProductId()!=null){
            Product oldProduct = productRepository.findById(addUpdateProductRequest.getProductId())
                    .orElseThrow(()->
                            new ResourceNotFoundException(String.format("Product with product_id : %s not found", addUpdateProductRequest.getProductId()), 0)
                    );
            List<Category> categories = categoryRepository.findAllByCategoryNameIn(addUpdateProductRequest.getCategories());

            oldProduct.setAboutProduct(addUpdateProductRequest.getAboutProduct());
            oldProduct.setProductImg(addUpdateProductRequest.getProductImg());
            oldProduct.setProductName(addUpdateProductRequest.getProductName());
            oldProduct.setProductPrice(addUpdateProductRequest.getProductPrice());
            oldProduct.setInStockQuantity(addUpdateProductRequest.getInStockQuantity());
            oldProduct.setIsAvailable(addUpdateProductRequest.getIsAvailable());
            oldProduct.setCategories(categories);

            return modelMapper.map(productRepository.save(oldProduct), AddUpdateProductResponse.class);
        }

        //Add new product
        else {
            User added_by = userRepository.findById(addUpdateProductRequest.getAddedBy())
                    .orElseThrow( () ->
                            new ResourceNotFoundException(String.format("User with uuid : %s not found", addUpdateProductRequest.getAddedBy()), 0)
                    );
            List<Category> categories = categoryRepository.findAllByCategoryNameIn(addUpdateProductRequest.getCategories());
            product.setCategories(categories);
            product.setAddedBy(added_by);
            return modelMapper.map(productRepository.save(product), AddUpdateProductResponse.class);
        }
    }

    @Override
    public void deleteProduct(Integer product_id) {
        Product oldProduct = productRepository.findById(product_id)
                .orElseThrow(()->
                        new ResourceNotFoundException(String.format("Product with product_id : %s not found", product_id), 0)
                );
        productRepository.delete(oldProduct);
    }

    @Override
    public List<AddUpdateProductResponse> bulkUploadProducts(MultipartFile file, String added_by) {
        try {
            List<AddUpdateProductRequest> productRequestList = ProductExcelHelper.convertExcelToProduct(file.getInputStream(), added_by);

            User addedByUser = userRepository.findById(added_by)
                    .orElseThrow( () ->
                            new ResourceNotFoundException(String.format("User with uuid : %s not found", added_by), 0)
                    );

            List<Product> products = productRequestList.stream()
                    .map((product)->
                        modelMapper.map(product, Product.class)
                    ).collect(Collectors.toList());

            products.forEach((product -> product.setAddedBy(addedByUser)));

            for (int i=0;i<productRequestList.size();i++){
                List<Category> categories = categoryRepository.findAllByCategoryNameIn(productRequestList.get(i).getCategories());
                products.get(i).setCategories(categories);
            }

            productRepository.saveAll(products);
            return products.stream()
                    .map((product)->{
                        return modelMapper.map(product, AddUpdateProductResponse.class);
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<AddUpdateProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map((product)-> modelMapper.map(product, AddUpdateProductResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public AddUpdateProductResponse getProductById(Integer product_id) {
        return null;
    }
}
