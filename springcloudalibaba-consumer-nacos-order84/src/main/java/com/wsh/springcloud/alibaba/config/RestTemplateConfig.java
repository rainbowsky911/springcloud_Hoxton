package com.wsh.springcloud.alibaba.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description RestTemplate负载均衡配置类
 * @Date 2020/9/6 16:05
 * @Author weishihuai
 * 说明:
 */
@Configuration
public class RestTemplateConfig {
    /**
     * 负载均衡配置类
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
