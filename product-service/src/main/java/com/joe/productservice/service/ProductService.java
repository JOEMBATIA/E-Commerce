package com.joe.productservice.service;

import com.joe.productservice.dto.ProductRequest;
import com.joe.productservice.dto.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
