package com.codearti.servicenotify.services.impl;

import com.codearti.servicenotify.dto.NotifyResponse;
import com.codearti.servicenotify.repository.NotifyOrderRepository;
import com.codearti.servicenotify.services.NotifyOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyOrderServiceImpl implements NotifyOrderService {

    private final NotifyOrderRepository notifyOrderRepository;


    @Override
    public Mono<NotifyResponse> getNotify(String id) {
        return notifyOrderRepository.findById(id)
                .map(x -> new NotifyResponse()
                        .id(x.getId())
                        .owner(x.getOwner())
                        .status(x.getStatus())
                        .dateString(x.getDateString())
                );
    }

    @Override
    public Flux<NotifyResponse> getAllNotify() {
        return notifyOrderRepository.findAll()
                .map(x -> new NotifyResponse()
                        .id(x.getId())
                        .owner(x.getOwner())
                        .status(x.getStatus())
                        .dateString(x.getDateString())
                );
    }
}
