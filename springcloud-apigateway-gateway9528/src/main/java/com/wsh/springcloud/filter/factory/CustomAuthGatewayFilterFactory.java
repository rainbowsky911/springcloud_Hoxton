package com.wsh.springcloud.filter.factory;

import com.wsh.springcloud.filter.CustomAuthFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 过滤器工厂
 * @Date 2020/8/21 20:40
 * @Author weishihuai
 * 说明:
 */
@Component
public class CustomAuthGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new CustomAuthFilter();
    }
}
