package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.entity.Product;
import com.express.grocery.Express.Grocery.entity.User;
import com.express.grocery.Express.Grocery.exception.ResourceNotFoundException;
import com.express.grocery.Express.Grocery.repository.ProductRepository;
import com.express.grocery.Express.Grocery.repository.UserRepository;
import com.express.grocery.Express.Grocery.service.ProductService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public AddUpdateProductResponse addUpdateProduct(AddUpdateProductRequest addUpdateProductRequest) {
        Product product = modelMapper.map(addUpdateProductRequest, Product.class);
        //Update product
        if (addUpdateProductRequest.getProduct_id()!=null){
            Product oldProduct = productRepository.findById(addUpdateProductRequest.getProduct_id())
                    .orElseThrow(()->
                            new ResourceNotFoundException(String.format("Product with product_id : %s not found", addUpdateProductRequest.getProduct_id()), 0)
                    );
            oldProduct.setAbout_product(addUpdateProductRequest.getAbout_product());
            oldProduct.setProduct_img(addUpdateProductRequest.getProduct_img());
            oldProduct.setProduct_name(addUpdateProductRequest.getProduct_name());
            oldProduct.setProduct_price(addUpdateProductRequest.getProduct_price());
            oldProduct.setIn_stock_quantity(addUpdateProductRequest.getIn_stock_quantity());
            oldProduct.setIs_available(addUpdateProductRequest.getIs_available());

            return modelMapper.map(productRepository.save(oldProduct), AddUpdateProductResponse.class);
        }

        //Add new product
        else {
            User added_by = userRepository.findById(addUpdateProductRequest.getAdded_by())
                    .orElseThrow( () ->
                            new ResourceNotFoundException(String.format("User with uuid : %s not found", addUpdateProductRequest.getAdded_by()), 0)
                    );

            product.setAdded_by(added_by);
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
    public List<AddUpdateProductResponse> bulkUploadProducts(MultipartFile file) {
        return List.of();
    }

    @Override
    public List<AddUpdateProductResponse> getAllProducts() {
        return List.of();
    }

    @Override
    public AddUpdateProductResponse getProductById(Integer product_id) {
        return null;
    }
}
