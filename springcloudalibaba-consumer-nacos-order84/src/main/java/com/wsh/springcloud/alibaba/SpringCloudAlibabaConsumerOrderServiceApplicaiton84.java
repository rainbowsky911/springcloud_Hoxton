package com.wsh.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@SpringBootApplication
//开启远程调用
@EnableFeignClients
public class SpringCloudAlibabaConsumerOrderServiceApplicaiton84 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaConsumerOrderServiceApplicaiton84.class, args);
    }
}
