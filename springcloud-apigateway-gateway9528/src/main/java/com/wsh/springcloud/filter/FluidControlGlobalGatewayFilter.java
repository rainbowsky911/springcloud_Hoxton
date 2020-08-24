package com.wsh.springcloud.filter;

import com.alibaba.fastjson.JSON;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Bucket4j;
import io.github.bucket4j.Refill;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.filter.FluidControlGlobalGatewayFilter.java
 * @Description: 自定义全局过滤器 - 限流过滤器(Token令牌桶算法)
 * @author: weishihuai
 * @date: 2020/8/22 11:30
 */
//@Component
public class FluidControlGlobalGatewayFilter implements GlobalFilter, Ordered {
    private static final Logger logger = LoggerFactory.getLogger(FluidControlGlobalGatewayFilter.class);
    /**
     * 桶的最大容量，即能装载Token令牌的最大数量
     */
    private int capacity = 5;
    /**
     * 每次Token补充量
     */
    private int refillTokens = 1;
    /**
     * 补充Token的时间间隔
     */
    private Duration duration = Duration.ofSeconds(1);

    private static final Map<String, Bucket> BUCKET_CACHE = new ConcurrentHashMap<>();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        Bucket bucket = BUCKET_CACHE.computeIfAbsent(ip,
                k -> {
                    Refill refill = Refill.greedy(refillTokens, duration);
                    Bandwidth limit = Bandwidth.classic(capacity, refill);
                    return Bucket4j.builder().addLimit(limit).build();
                });
        logger.info("客户端IP地址: " + ip + ", 获取: " + bucket.getAvailableTokens());

        if (bucket.tryConsume(1)) {
            //获取token成功,执行下一个过滤器逻辑
            return chain.filter(exchange);
        } else {
            ServerHttpResponse response = exchange.getResponse();
            Map<String, Object> data = new HashMap<>();
            data.put("code", HttpStatus.NOT_ACCEPTABLE);
            data.put("msg", "超过访问次数！");
            byte[] datas = JSON.toJSONString(data).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
