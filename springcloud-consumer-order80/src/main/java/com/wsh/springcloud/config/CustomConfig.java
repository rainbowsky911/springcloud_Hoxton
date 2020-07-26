package com.wsh.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Description 全局配置文件类
 * @Date 2020/7/25 15:45
 * @Author weishihuai
 * 说明: 此处配置也可以放在主启动类中
 */
@Configuration
public class CustomConfig {

    //注册到spring ioc容器中
    @Bean
    @LoadBalanced  //开启负载均衡功能
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}