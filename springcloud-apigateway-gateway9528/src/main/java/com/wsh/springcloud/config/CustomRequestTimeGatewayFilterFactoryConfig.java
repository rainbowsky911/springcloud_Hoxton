package com.wsh.springcloud.config;

import com.wsh.springcloud.filter.factory.CustomRequestTimeGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 注册自定义过滤器工厂
 * @Date 2020/8/21 21:37
 * @Author weishihuai
 * 说明:
 */
@Configuration
public class CustomRequestTimeGatewayFilterFactoryConfig {
    @Bean
    public CustomRequestTimeGatewayFilterFactory customRequestTimeGatewayFilterFactory() {
        return new CustomRequestTimeGatewayFilterFactory();
    }
}
