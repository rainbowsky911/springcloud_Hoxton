package com.wsh.springcloud.alibaba.controller;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class PaymentController {
    @Value("${server.port}")
    private String serverPort;

    /**
     * 模拟静态数据
     */
    private static HashMap<Long, Payment> hashMap = new HashMap<>();

    static {
        hashMap.put(1L, new Payment(1L, "001"));
        hashMap.put(2L, new Payment(2L, "002"));
        hashMap.put(3L, new Payment(3L, "003"));
    }

    @GetMapping(value = "/payment/{id}")
    public JsonResult<Payment> payment(@PathVariable("id") Long id) {
        Payment payment = hashMap.get(id);
        return new JsonResult(200, "serial: " + payment.getSerial() + ",serverPort:  " + serverPort, payment);
    }

}
