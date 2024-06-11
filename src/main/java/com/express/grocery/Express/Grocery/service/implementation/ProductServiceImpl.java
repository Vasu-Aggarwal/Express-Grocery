package com.express.grocery.Express.Grocery.service.implementation;

import com.express.grocery.Express.Grocery.dto.CategoryDto;
import com.express.grocery.Express.Grocery.dto.request.AddUpdateProductRequest;
import com.express.grocery.Express.Grocery.dto.response.AddUpdateProductResponse;
import com.express.grocery.Express.Grocery.dto.response.AllProductListResponse;
import com.express.grocery.Express.Grocery.entity.CartDetail;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.*;
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
            Product saveProduct = productRepository.save(oldProduct);
            //Apply category discount if any
            AddUpdateProductResponse productResponse = modelMapper.map(saveProduct, AddUpdateProductResponse.class);
            productDiscountHelper(productResponse);
            return productResponse;
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
            Product saveProduct = productRepository.save(product);
            AddUpdateProductResponse productResponse = modelMapper.map(saveProduct, AddUpdateProductResponse.class);
            productDiscountHelper(productResponse);
            return productResponse;
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
    public AllProductListResponse allProductList(String userUuid, Integer pageNumber, Integer pageSize, String sortBy) {

        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException("User not found", 0));

        Map<Integer, Integer> productInCartQuantity = user.getCart().getCartDetails().stream()
                .collect(Collectors.toMap(
                        cartDetail -> cartDetail.getProduct().getProductId(),
                        CartDetail::getProductQuantity
                ));

        Pageable pageable =  PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        Page<Product> productsPage = productRepository.findAll(pageable);
        List<Product> products = productsPage.getContent();
        List<AddUpdateProductResponse> productResponses = products.stream()
                .map((product)->{
                        AddUpdateProductResponse res =  modelMapper.map(product, AddUpdateProductResponse.class);
                        res.setInCartQuantity(Optional.ofNullable(productInCartQuantity.get(product.getProductId())).orElse(0));
                        return res;
                    })
                .collect(Collectors.toList());

        //Get category discount of each product
        for (AddUpdateProductResponse product: productResponses){
            productDiscountHelper(product);
        }

        AllProductListResponse productListResponse = new AllProductListResponse();
        productListResponse.setProducts(productResponses);
        productListResponse.setTotalProducts(productsPage.getTotalElements());
        productListResponse.setPageNumber(productsPage.getNumber());
        productListResponse.setPageSize(productsPage.getSize());
        productListResponse.setIsLastPage(productsPage.isLast());
        productListResponse.setTotalPages(productsPage.getTotalPages());
        return productListResponse;
    }

    public static void productDiscountHelper(AddUpdateProductResponse product){
        List<CategoryDto> applicableDiscountedCategories = product.getCategories().stream().filter(CategoryDto::getIsCoupon).collect(Collectors.toList());
        Optional<CategoryDto> maxDiscountCategory = applicableDiscountedCategories.stream().max(Comparator.comparingDouble(cat-> cat.getCoupon().getDiscountPercent()));
        maxDiscountCategory.ifPresentOrElse(categoryDto -> {
            //find the total discount amount on the product
            double findMaxDiscountAmount = product.getProductPrice() * ((double) (categoryDto.getCoupon().getDiscountPercent()) /100);
            double discountAmount;
            //if discount amount is greater or equal then apply max discount otherwise the discount percent of the coupon
            if (findMaxDiscountAmount >= categoryDto.getCoupon().getMaxDiscount()){
                discountAmount = product.getProductPrice() - categoryDto.getCoupon().getMaxDiscount();
            } else {
                discountAmount = product.getProductPrice() - findMaxDiscountAmount;
            }
            product.setDiscountOnCategory(categoryDto.getCoupon().getDiscountPercent());
            product.setProductDiscountedPrice(discountAmount);
        }, () -> {
            product.setDiscountOnCategory(0);
            product.setProductDiscountedPrice(product.getProductPrice());
        });
    }

    @Override
    public AddUpdateProductResponse getProductById(Integer product_id) {
        Product product = productRepository.findById(product_id).orElseThrow(()-> new ResourceNotFoundException(String.format("Product with id: %s not found", product_id), 0));
        AddUpdateProductResponse productResponse = modelMapper.map(product, AddUpdateProductResponse.class);
        productDiscountHelper(productResponse);
        return productResponse;
    }

    @Override
    public AddUpdateProductResponse getProductByName(String userUuid, String productName) {
        User user = userRepository.findById(userUuid).orElseThrow(()-> new ResourceNotFoundException("User not found", 0));

        Product product = productRepository.findByProductNameContainingIgnoreCase(productName).orElseThrow(()-> new ResourceNotFoundException(String.format("Product not found: %s", productName), 0));
        AddUpdateProductResponse productResponse = modelMapper.map(product, AddUpdateProductResponse.class);
        productDiscountHelper(productResponse);
        Optional<CartDetail> cartDetails = user.getCart()
                .getCartDetails()
                .stream()
                .filter(cartDetail -> cartDetail.getProduct().getProductId().equals(product.getProductId()))
                .findFirst();
        cartDetails.ifPresent(cartDetail -> productResponse.setInCartQuantity(cartDetail.getProductQuantity()));
        return productResponse;
    }

}
