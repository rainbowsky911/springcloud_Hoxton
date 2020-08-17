package com.wsh.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.wsh.springcloud.feign.PaymentHystrixFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class OrderHystrixController {
    @Resource
    private PaymentHystrixFeignClient paymentHystrixFeignClient;

    @GetMapping("/consumer/payment/hystrix/success/{id}")
    public String paymentInfoSuccess(@PathVariable("id") Integer id) {
        return paymentHystrixFeignClient.paymentInfoSuccess(id);
    }

    @GetMapping("/consumer/payment/hystrix/fail/{id}")
    @HystrixCommand(fallbackMethod = "paymentTimeOutFallbackMethod", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "1500")
    })
    public String paymentInfoFail(@PathVariable("id") Integer id) {
        int age = 10 / 0;
        return paymentHystrixFeignClient.paymentInfoFail(id);
    }

    public String paymentTimeOutFallbackMethod(@PathVariable("id") Integer id) {
        return "sorry,[" + id + "], the payment service is not available! \";";
    }

}
