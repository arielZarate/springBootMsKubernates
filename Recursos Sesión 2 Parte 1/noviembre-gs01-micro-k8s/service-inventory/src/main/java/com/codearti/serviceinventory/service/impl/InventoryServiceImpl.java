package com.codearti.serviceinventory.service.impl;

import com.codearti.serviceinventory.dto.InventoryRequest;
import com.codearti.serviceinventory.dto.InventoryResponse;
import com.codearti.serviceinventory.model.mapper.InventoryMapper;
import com.codearti.serviceinventory.repository.InventoryRepository;
import com.codearti.serviceinventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Override
    public Mono<InventoryResponse> createOrder(Mono<InventoryRequest> inventory) {
        return inventory.map(InventoryMapper.INSTANCE::requestToModel)
                .flatMap(value -> inventoryRepository.existsByCode(value.getCode())
                        .flatMap(y -> {
                            if (y) {
                                return Mono.empty();
                            }
                            return inventoryRepository.save(value);
                        })
                )
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }

    @Override
    public Mono<InventoryResponse> getInventory(String code) {
        return inventoryRepository.findByCode(code)
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }

    @Override
    public Flux<InventoryResponse> getList() {
        return inventoryRepository.findAll()
                .map(InventoryMapper.INSTANCE::modelToResponse);
    }
}