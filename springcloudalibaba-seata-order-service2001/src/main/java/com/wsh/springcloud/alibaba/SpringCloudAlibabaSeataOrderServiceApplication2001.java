package com.wsh.springcloud.alibaba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
//开启远程服务调用功能
@EnableFeignClients
//取消数据源的自动创建
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SpringCloudAlibabaSeataOrderServiceApplication2001 {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudAlibabaSeataOrderServiceApplication2001.class, args);
    }
}
