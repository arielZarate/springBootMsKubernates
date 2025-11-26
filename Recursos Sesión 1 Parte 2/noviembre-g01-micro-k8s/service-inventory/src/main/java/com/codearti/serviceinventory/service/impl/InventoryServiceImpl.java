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
    private final  InventoryRepository inventoryRepository;

    private final InventoryMapper mapper;
//    @Override
//    public Mono<InventoryResponse> createOrder(Mono<InventoryRequest> inventoryRequest) {
//        return inventoryRequest
//                .map(InventoryMapper.INSTANCE::requestToModel)
//                .flatMap(inventoryModel ->
//                        inventoryRepository.existsByCode(inventoryModel())
//                                .filter(exists -> !exists)  // Filtra solo si no existe
//                                .switchIfEmpty(Mono.error(new IllegalArgumentException(
//                                        "Inventory with code " + inventoryModel.getCode() + " already exists")))
//                                .then(inventoryRepository.save(inventoryModel))
//                )
//                .map(InventoryMapper.INSTANCE::modelToResponse);
//    }


    @Override
    public Mono<InventoryResponse> createOrder(Mono<InventoryRequest> inventoryRequest) {

        return inventoryRequest
                .map(mapper::requestToModel)
                .flatMap(inventoryModel ->
                        inventoryRepository.existsByCode(inventoryModel.getCode())
                                .flatMap(exists -> {
                                    if (exists) {
                                        return Mono.error(new IllegalArgumentException(
                                                "Inventory with code " + inventoryModel.getCode() + " already exists"));
                                    }
                                    return inventoryRepository.save(inventoryModel);
                                })
                )
                .map(mapper::modelToResponse);
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
