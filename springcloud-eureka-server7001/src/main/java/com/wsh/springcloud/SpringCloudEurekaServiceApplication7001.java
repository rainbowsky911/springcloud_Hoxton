package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @Description Eureka服务注册中心
 * @Date 2020/7/26 8:55
 * @Author weishihuai
 * 说明:
 */
@SpringBootApplication
//@EnableEurekaServer: 开启Eureka服务注册和发现功能(服务端)
@EnableEurekaServer
public class SpringCloudEurekaServiceApplication7001 {
    public static void main(String[] args) {
        SpringApplication.run(SpringCloudEurekaServiceApplication7001.class, args);
    }
}
