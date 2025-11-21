package com.codearti.servicenotify.services;

import com.codearti.servicenotify.dto.NotifyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface NotifyOrderService {
    Mono<NotifyResponse> getNotify(String id);
    Flux<NotifyResponse> getAllNotify();
}

