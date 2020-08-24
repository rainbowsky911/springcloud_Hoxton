package com.wsh.springcloud.keyResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description 自定义URI KeyResolver
 * @Date 2020/8/23 21:01
 * @Author weishihuai
 * 说明: 获取请求地址的uri作为限流key
 */
@Component
public class UriKeyResolver implements KeyResolver {
    private static final Logger logger = LoggerFactory.getLogger(UriKeyResolver.class);

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String path = exchange.getRequest().getURI().getPath();
        logger.info("interface url : {}", path);
        //将接口URL作为限流Key
        return Mono.just(path);
    }
}
