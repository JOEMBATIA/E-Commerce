package com.joe.productservice.service.impl;

import com.joe.productservice.dto.ProductRequest;
import com.joe.productservice.dto.ProductResponse;
import com.joe.productservice.error.ProductNotFoundException;
import com.joe.productservice.model.Product;
import com.joe.productservice.repository.ProductRepository;
import com.joe.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .description(productRequest.getDescription())
                .name(productRequest.getName())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("Product {} created successfully ", product.getName());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapProductResponse).toList();
    }

    @Override
    public ProductResponse updateProduct(ProductRequest request, String id) throws ProductNotFoundException {

        Optional<Product> existentProduct = productRepository.findById(id);

        if (existentProduct.isEmpty()) {
            throw new ProductNotFoundException("Product does not exist");
        }

        Product updatedProduct = existentProduct.get();

        updatedProduct = Product.builder()
                .id(id)
                .price(request.getPrice())
                .name(request.getName())
                .description(request.getDescription())
                .build();

        productRepository.save(updatedProduct);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .name(updatedProduct.getName())
                .description(updatedProduct.getDescription())
                .price(updatedProduct.getPrice())
                .build();


    }

    @Override
    public String deleteProduct(String id) throws ProductNotFoundException {

        Optional<Product> deletedProduct = productRepository.findById(id);

        if (deletedProduct.isEmpty()){
            throw new ProductNotFoundException("Product not existent");
        }

        String message = "Product " + deletedProduct.get().getName() + " deleted successfully";

        productRepository.deleteById(id);

        return message;
    }

    private ProductResponse mapProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
