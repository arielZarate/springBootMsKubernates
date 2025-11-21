package com.codearti.serviceinventory.expose;

import com.codearti.serviceinventory.api.InventoryApiDelegate;
import com.codearti.serviceinventory.dto.InventoryRequest;
import com.codearti.serviceinventory.dto.InventoryResponse;
import com.codearti.serviceinventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
@RequiredArgsConstructor
public class InventoryApiImpl implements InventoryApiDelegate {
    private final InventoryService inventoryService;

    @Override
    public Mono<InventoryResponse> getInventory(String productId,
                                                ServerWebExchange exchange) {
        log.info("GetInventory");

        return inventoryService.getInventory(productId)
                .doOnSubscribe(value -> log.info("Fetching inventory for productId: {}", productId))
                .doOnSuccess(value -> log.info("Successfully fetched inventory for productId: {}", productId))
                .doOnError(ex -> log.error("Error fetching inventory for productId: {}", productId, ex));
    }

    @Override
    public Flux<InventoryResponse> listInventory(ServerWebExchange exchange) {
        return inventoryService.getList()
                .delayElements(java.time.Duration.ofSeconds(3))
                .doOnSubscribe(a -> log.warn("LIst Inventory"))
                .doOnComplete(() -> log.warn("List Inventory Complete"))
                .doOnError(e -> log.error("Error List Inventory", e));
    }

    @Override
    public Mono<InventoryResponse> registerInventory(Mono<InventoryRequest> inventoryRequest,
                                                     ServerWebExchange exchange) {
        return inventoryService.createOrder(inventoryRequest);
    }
}
