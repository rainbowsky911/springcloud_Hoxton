package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
//@EnableCircuitBreaker开启Hystrix断路器功能
@EnableCircuitBreaker
public class HystrixPaymentServiceApplication8001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixPaymentServiceApplication8001.class, args);
    }
}
