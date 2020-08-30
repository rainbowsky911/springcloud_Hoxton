package com.wsh.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    @GetMapping(value = "/payment/nacos/{name}")
    public String getPayment(@PathVariable("name") String name) {
        return "[springcloudalibaba-provider-payment]注册到Nacos, 端口号: " + serverPort + ", this name is " + name;
    }

}
