package com.wsh.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @Description 服务负载均衡调用测试
 * @Date 2020/8/30 10:30
 * @Author weishihuai
 * 说明:
 */
@RestController
public class OrderNacosController {
    /**
     * 注入restTemplate
     */
    @Resource
    private RestTemplate restTemplate;

    /**
     * 获取到服务提供者URL
     */
    @Value("${service-url.provider-payment}")
    private String paymentProviderURL;

    @GetMapping(value = "/consumer/payment/nacos/{name}")
    public String paymentInfo(@PathVariable("name") String name) {
        return restTemplate.getForObject(paymentProviderURL + "/payment/nacos/" + name, String.class);
    }

}
