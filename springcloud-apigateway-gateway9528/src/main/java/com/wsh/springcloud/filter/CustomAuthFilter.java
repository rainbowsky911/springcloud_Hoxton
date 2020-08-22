package com.wsh.springcloud.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.filter.CustomAuthFilter.java
 * @Description: 自定义权限校验过滤器
 * @author: weishihuai
 * @date: 2020/8/21 15:55
 * 说明: 判断请求头中是否存在token信息,如果
 */
public class CustomAuthFilter implements GatewayFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取请求头中的token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StringUtils.isNotBlank(token)) {
            //执行下一个过滤器
            return chain.filter(exchange);
        } else {
            //token为空,结束
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            return exchange.getResponse().setComplete();
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
