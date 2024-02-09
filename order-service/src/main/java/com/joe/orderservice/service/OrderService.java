package com.joe.orderservice.service;

import com.joe.orderservice.dto.OrderRequest;

public interface OrderService {
    void createOrder(OrderRequest orderRequest);
}
