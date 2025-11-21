package com.codearti.serviceinventory.model.mapper;

import com.codearti.serviceinventory.dto.InventoryRequest;
import com.codearti.serviceinventory.dto.InventoryResponse;
import com.codearti.serviceinventory.model.entity.ProductsModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    InventoryMapper INSTANCE = Mappers.getMapper(InventoryMapper.class);

    @Mapping(target = "code", source = "idProduct")
    ProductsModel requestToModel(InventoryRequest request);

    @Mapping(target = "idProduct", source = "code")
    InventoryResponse modelToResponse(ProductsModel model);
}

