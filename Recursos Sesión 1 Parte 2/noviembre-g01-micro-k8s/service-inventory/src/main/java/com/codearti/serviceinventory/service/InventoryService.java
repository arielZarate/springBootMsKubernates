package com.codearti.serviceinventory.service;

import com.codearti.serviceinventory.dto.InventoryRequest;
import com.codearti.serviceinventory.dto.InventoryResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface InventoryService{

    public Mono<InventoryResponse> createOrder(Mono<InventoryRequest> inventory);
    public Mono<InventoryResponse> getInventory(String code);
    public Flux<InventoryResponse> getList();
}
