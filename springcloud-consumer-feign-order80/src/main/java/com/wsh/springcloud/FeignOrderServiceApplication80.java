package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
//开启Feign远程服务调用功能
@EnableFeignClients
public class FeignOrderServiceApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(FeignOrderServiceApplication80.class, args);
    }
}
