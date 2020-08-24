package com.wsh.springcloud.keyResolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description 自定义主机名称 KeyResolver
 * @Date 2020/8/23 21:00
 * @Author weishihuai
 * 说明: 获取请求用户ip作为限流key
 */
@Component
@Primary
public class HostNameAddressKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
    }
}
