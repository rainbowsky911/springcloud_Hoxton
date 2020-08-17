package com.wsh.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@SpringBootApplication
//开启仪表盘监控功能
@EnableHystrixDashboard
public class HystrixDashboardServiceApplication9001 {
    public static void main(String[] args) {
        SpringApplication.run(HystrixDashboardServiceApplication9001.class, args);
    }
}
