package com.joe.inventoryservice.controller;

import com.joe.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping("/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean fetchInventoryIsInStock(@PathVariable("sku-code") String skuCode){
        return inventoryService.IsInStock(skuCode);
    }
}
