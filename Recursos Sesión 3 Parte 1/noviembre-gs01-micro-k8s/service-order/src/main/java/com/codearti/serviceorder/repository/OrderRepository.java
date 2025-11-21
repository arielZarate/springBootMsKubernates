package com.codearti.serviceorder.repository;

import com.codearti.serviceorder.model.entity.OrderModel;
import org.springframework.data.r2dbc.repository.R2dbcRepository;

public interface OrderRepository extends R2dbcRepository<OrderModel, Integer> {
}
