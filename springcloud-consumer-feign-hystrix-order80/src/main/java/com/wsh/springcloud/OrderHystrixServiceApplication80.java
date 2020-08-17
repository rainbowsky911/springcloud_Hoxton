package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启远程服务调用功能
@EnableFeignClients
//开启Hystrix断路器功能
@EnableHystrix
public class OrderHystrixServiceApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderHystrixServiceApplication80.class, args);
    }
}
