package com.joe.productservice.controller;

import com.joe.productservice.dto.ProductRequest;
import com.joe.productservice.dto.ProductResponse;
import com.joe.productservice.error.ProductNotFoundException;
import com.joe.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);

    }

    @GetMapping("/get")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }


    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductResponse updateProduct(
            @RequestBody ProductRequest request, @PathVariable String id) throws ProductNotFoundException {
        return productService.updateProduct(request, id);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable String id) throws ProductNotFoundException {
        return productService.deleteProduct(id);
    }
}
