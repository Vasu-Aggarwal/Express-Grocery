package com.express.grocery.Express.Grocery.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AllProductListResponse {
    private List<AddUpdateProductResponse> products;
    private Integer pageNumber;
    private Integer pageSize;
    private Long totalProducts;
    private Integer totalPages;
    private Boolean isLastPage;
}
