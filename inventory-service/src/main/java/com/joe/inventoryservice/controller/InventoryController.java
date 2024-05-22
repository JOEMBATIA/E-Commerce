package com.joe.inventoryservice.controller;

import com.joe.inventoryservice.dto.InventoryResponse;
import com.joe.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> fetchInventoryIsInStock(@RequestParam List<String> skuCode){
        return inventoryService.IsInStock(skuCode);
    }

    @GetMapping("/allStock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getAllInventory(){
        return inventoryService.getAllStock();
    }
}
