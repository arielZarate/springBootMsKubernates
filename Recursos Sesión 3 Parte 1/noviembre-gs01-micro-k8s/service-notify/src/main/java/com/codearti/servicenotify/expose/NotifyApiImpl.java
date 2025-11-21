package com.codearti.servicenotify.expose;

import com.codearti.servicenotify.api.NotifyApiDelegate;
import com.codearti.servicenotify.dto.NotifyResponse;
import com.codearti.servicenotify.services.NotifyOrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class NotifyApiImpl implements NotifyApiDelegate {

    private final NotifyOrderService notifyOrderService;

    @Override
    public Flux<NotifyResponse> findAllNotify(ServerWebExchange exchange) {
        return notifyOrderService.getAllNotify();
    }

    @Override
    public Mono<NotifyResponse> getNotifyById(String notifyId,
                                              ServerWebExchange exchange) {
        return notifyOrderService.getNotify(notifyId);
    }
}
