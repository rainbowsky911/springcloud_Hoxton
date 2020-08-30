package com.wsh.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class SpringCloudAlibabaPaymentServiceApplication9002 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaPaymentServiceApplication9002.class, args);
    }
}
