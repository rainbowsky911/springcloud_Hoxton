package com.wsh.springcloud.controller;

import com.wsh.springcloud.service.PaymentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @GetMapping("/payment/hystrix/success/{id}")
    public String paymentInfoSuccess(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoSuccess(id);
    }

    @GetMapping("/payment/hystrix/fail/{id}")
    public String paymentInfoFail(@PathVariable("id") Integer id) {
        return paymentService.paymentInfoFail(id);
    }

    //服务熔断
    @GetMapping("/payment/testCircuitBreaker/{id}")
    public String testCircuitBreaker(@PathVariable("id") Integer id) {
        return paymentService.testCircuitBreaker(id);
    }

}
