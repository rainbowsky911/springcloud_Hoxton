package com.wsh.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description 测试Controller
 * @Date 2020/8/3 20:16
 * @Author weishihuai
 * 说明:
 */
@RestController
public class OrderController {
    //指定服务提供者的application-name,其实也是注册进Consul中的服务名称
    private static final String INVOKE_URL = "http://springcloud-consul-provider-payment";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/consumer/payment/consul")
    public String paymentInfo() {
        String result = restTemplate.getForObject(INVOKE_URL + "/payment/consul", String.class);
        return result;
    }
}
