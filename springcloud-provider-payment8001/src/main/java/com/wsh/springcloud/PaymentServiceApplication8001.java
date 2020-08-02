package com.wsh.springcloud;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @Description 主启动类
 * @Date 2020/7/25 9:39
 * @Author weishihuai
 * 说明:
 */
@SpringBootApplication
@EnableEurekaClient
@EnableDiscoveryClient
@MapperScan("com.wsh.springcloud.mapper")
public class PaymentServiceApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(PaymentServiceApplication8001.class, args);
    }
}
