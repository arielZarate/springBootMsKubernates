package com.codearti.servicenotify.repository;

import com.codearti.servicenotify.models.entity.NotifyOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyOrderRepository extends ReactiveCrudRepository<NotifyOrder, String> {

}
