package com.joe.productservice.error;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String message){
        super(message);
    }
}
