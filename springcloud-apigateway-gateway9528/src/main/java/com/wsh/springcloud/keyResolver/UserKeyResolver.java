package com.wsh.springcloud.keyResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description 自定义用户 KeyResolver
 * @Date 2020/8/23 21:02
 * @Author weishihuai
 * 说明: 获取请求用户id作为限流key
 */
@Component
@Primary
public class UserKeyResolver implements KeyResolver {
    private static final Logger logger = LoggerFactory.getLogger(UserKeyResolver.class);

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        //获取请求参数中的userId作为限流Key
        String userId = exchange.getRequest().getQueryParams().getFirst("userId");
        logger.info("userKey: {}", userId);
        return Mono.just(userId);
    }
}
