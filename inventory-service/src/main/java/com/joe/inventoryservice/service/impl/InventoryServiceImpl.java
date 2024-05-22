package com.joe.inventoryservice.service.impl;

import com.joe.inventoryservice.dto.InventoryResponse;
import com.joe.inventoryservice.model.Inventory;
import com.joe.inventoryservice.repository.InventoryRepository;
import com.joe.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {

    private final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> IsInStock(List<String> skuCode) {
        return inventoryRepository.findBySkuCodeIn(skuCode)
                .stream()
                .map(this::mapToInventoryDto)
                .toList();
    }

    @Override
    public List<InventoryResponse> getAllStock() {
        List<Inventory> allStock = inventoryRepository.findAll();

        return allStock.stream()
                .map(this::mapToInventoryDto)
                .toList();
    }

    private InventoryResponse mapToInventoryDto(Inventory inventory) {
        return InventoryResponse.builder()
                .skuCode(inventory.getSkuCode())
                .isInStock(inventory.getQuantity() > 0)
                .build();
    }


}