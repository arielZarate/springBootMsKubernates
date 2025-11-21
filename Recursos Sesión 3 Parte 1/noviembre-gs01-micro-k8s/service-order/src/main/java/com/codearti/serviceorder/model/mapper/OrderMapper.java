package com.codearti.serviceorder.model.mapper;

import com.codearti.serviceorder.dto.OrderRequest;
import com.codearti.serviceorder.dto.OrderResponse;
import com.codearti.serviceorder.model.entity.OrderModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "codeProduct", source = "codeProduct")
    OrderModel requestToModel(OrderRequest orderRequest);

    @Mapping(target = "status", source = "statusOrder", qualifiedByName = "mapStatus")
    @Mapping(target = "createdAt", source = "dateOrder")
    OrderResponse modelToResponse(OrderModel orderModel);

    @Named("mapStatus")
    default OrderResponse.StatusEnum mapStatus(String statusOrder) {
        return OrderResponse.StatusEnum.fromValue(statusOrder);
    }
}
