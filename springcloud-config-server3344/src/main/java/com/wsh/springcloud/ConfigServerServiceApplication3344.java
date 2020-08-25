package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
//服务配置中心注解
@EnableConfigServer
public class ConfigServerServiceApplication3344 {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServerServiceApplication3344.class, args);
    }
}
