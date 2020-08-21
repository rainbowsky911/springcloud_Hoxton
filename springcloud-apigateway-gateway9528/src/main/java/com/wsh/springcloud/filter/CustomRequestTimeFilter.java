package com.wsh.springcloud.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @Description: 自定义请求时间记录过滤器
 * @author: weishihuai
 * @Date: 2020/8/21 15:27
 */
public class CustomRequestTimeFilter implements GatewayFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestTimeFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String rawPath = exchange.getRequest().getURI().getRawPath();
        logger.info(rawPath + "-----------pre前置处理----------");
        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).then().then(
                Mono.fromRunnable(() -> {
                    long endTime = System.currentTimeMillis();
                    logger.info(rawPath + "-----------post后置处理----------");
                    logger.info(exchange.getRequest().getURI().getRawPath() + "----> 耗时: " + (endTime - startTime) + "ms");
                })
        );
    }

    /**
     * 定义过滤器执行的顺序
     */
    @Override
    public int getOrder() {
        return 0;
    }

}
