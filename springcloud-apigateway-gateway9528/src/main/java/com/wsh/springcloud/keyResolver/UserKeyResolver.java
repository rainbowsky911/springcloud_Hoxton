package com.wsh.springcloud.keyResolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description 自定义用户 KeyResolver
 * @Date 2020/8/23 21:02
 * @Author weishihuai
 * 说明:
 */
@Component
public class UserKeyResolver implements KeyResolver {
    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        return null;
    }
}
