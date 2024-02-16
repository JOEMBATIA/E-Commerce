package com.joe.inventoryservice.service;

import com.joe.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> IsInStock(List<String> skuCode);
}
