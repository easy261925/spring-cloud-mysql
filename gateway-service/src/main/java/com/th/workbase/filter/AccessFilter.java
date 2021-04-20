package com.th.workbase.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Date 2021-04-20-9:12
 * @Author tangJ
 * @Description
 * @Version 1.0
 */
public class AccessFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        //用JWT解析token #todo
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
