package com.wsh.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description Consul测试Controller
 * @Date 2020/8/2 21:20
 * @Author weishihuai
 * 说明:
 */
@RestController
public class PaymentController {
    @Value("${server.port}")
    private String port;

    @RequestMapping(value = "/payment/consul")
    public String paymentConsul() {
        return "spring cloud with consul: " + port;
    }

}
