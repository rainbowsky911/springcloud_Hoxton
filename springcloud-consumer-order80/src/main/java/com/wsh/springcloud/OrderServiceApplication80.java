package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableEurekaClient
//@RibbonClient(name = "CLOUD-PAYMENT-SERVICE",configuration=MySelfRule.class)
public class OrderServiceApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication80.class, args);
    }
}
