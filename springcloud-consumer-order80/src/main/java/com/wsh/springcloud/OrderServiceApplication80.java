package com.wsh.springcloud;

import com.wsh.myrule.CustomRibbonConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@SpringBootApplication
@EnableEurekaClient
//指定我们自己配置的负载均衡策略
@RibbonClient(name = "SPRINGCLOUD-PAYMENT-SERVICE", configuration = CustomRibbonConfig.class)
public class OrderServiceApplication80 {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication80.class, args);
    }
}
