package com.wsh.springcloud.keyResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
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
public class HostNameAddressKeyResolver implements KeyResolver {
    private static final Logger logger = LoggerFactory.getLogger(HostNameAddressKeyResolver.class);

    @Override
    public Mono<String> resolve(ServerWebExchange exchange) {
        String hostAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        logger.info("IP address: {}", hostAddress);
        //将用户IP地址作为限流Key
        return Mono.just(hostAddress);
    }
}
