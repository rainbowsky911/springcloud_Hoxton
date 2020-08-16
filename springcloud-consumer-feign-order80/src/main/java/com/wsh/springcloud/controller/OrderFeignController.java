package com.wsh.springcloud.controller;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import com.wsh.springcloud.feign.PaymentServiceFeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description Feign控制层接口
 * @Date 2020/8/15 21:02
 * @Author weishihuai
 * 说明:
 */
@RestController
public class OrderFeignController {
    @Resource
    private PaymentServiceFeignClient paymentServiceFeignClient;

    @GetMapping(value = "/openfeign/consumer/payment/get/{id}")
    public JsonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        return paymentServiceFeignClient.getPaymentById(id);
    }

    @GetMapping(value = "/openfeign/consumer/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // OpenFeign客户端一般默认等待1秒钟
        return paymentServiceFeignClient.paymentFeignTimeout();
    }
}
