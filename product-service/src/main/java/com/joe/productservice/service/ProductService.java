package com.joe.productservice.service;

import com.joe.productservice.dto.ProductRequest;
import com.joe.productservice.dto.ProductResponse;
import com.joe.productservice.error.ProductNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse updateProduct(ProductRequest request, String id) throws ProductNotFoundException;

    String deleteProduct(String id) throws ProductNotFoundException;
}
