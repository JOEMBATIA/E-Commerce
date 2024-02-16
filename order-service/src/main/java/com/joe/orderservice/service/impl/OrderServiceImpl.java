package com.joe.orderservice.service.impl;

import com.joe.orderservice.dto.InventoryResponse;
import com.joe.orderservice.dto.OrderLineItemsDto;
import com.joe.orderservice.dto.OrderRequest;
import com.joe.orderservice.model.Order;
import com.joe.orderservice.model.OrderLineItems;
import com.joe.orderservice.repository.OrderRepository;
import com.joe.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    @Override
    public void createOrder(OrderRequest orderRequest) {
        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();
        Order order = Order.builder().orderNumber(UUID.randomUUID().toString()).orderLineItemsList(orderLineItemsList).build();

        List<String> skuCodes =
                order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

        InventoryResponse[] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/inventory",
                        UriBuilder -> UriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        assert inventoryResponses != null;
        boolean productIsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::getIsInStock);

        if (Boolean.TRUE.equals(productIsInStock)) {
            orderRepository.save(order);
        } else {
            throw new IllegalArgumentException("Item not available");
        }

        log.info("order {} placed successfully", order.getOrderNumber());
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

        return OrderLineItems.builder()
                .id(orderLineItemsDto.getId())
                .price(orderLineItemsDto.getPrice())
                .quantity(orderLineItemsDto.getQuantity())
                .skuCode(orderLineItemsDto.getSkuCode())
                .build();
    }
}
