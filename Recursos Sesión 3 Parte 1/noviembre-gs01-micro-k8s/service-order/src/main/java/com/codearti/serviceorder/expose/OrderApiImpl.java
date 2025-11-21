package com.codearti.serviceorder.expose;

import com.codearti.serviceorder.api.OrdenesApiDelegate;
import com.codearti.serviceorder.dto.OrderRequest;
import com.codearti.serviceorder.dto.OrderResponse;
import com.codearti.serviceorder.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderApiImpl implements OrdenesApiDelegate {
    private final OrderService orderService;

    @Override
    public Mono<OrderResponse> createOrder(Mono<OrderRequest> orderRequest,
                                           ServerWebExchange exchange) {
        return orderRequest.flatMap(orderService::createOrder);
    }

    @Override
    public Mono<OrderResponse> getOrder(Integer orderId,
                                        ServerWebExchange exchange) {
        return orderService.getOrderById(orderId);
    }

    @Override
    public Flux<OrderResponse> listOrders(ServerWebExchange exchange) {
        return orderService.getListOrders();
    }

    @Override
    public Mono<Void> updateOrder(Integer orderId,
                                  ServerWebExchange exchange) {
        return orderService.updateOrder(orderId)
                .then();
    }
}
