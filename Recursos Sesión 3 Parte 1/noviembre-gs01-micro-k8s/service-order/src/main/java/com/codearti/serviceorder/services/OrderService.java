package com.codearti.serviceorder.services;

import com.codearti.serviceorder.dto.OrderRequest;
import com.codearti.serviceorder.dto.OrderResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrderService {
    Mono<OrderResponse> createOrder(OrderRequest orderRequest);
    Mono<OrderResponse> getOrderById(int orderId);
    Flux<OrderResponse> getListOrders();
    Mono<OrderResponse> updateOrder(int orderId);
}
