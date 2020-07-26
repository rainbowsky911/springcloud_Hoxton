package com.wsh.springcloud.service.impl;

import com.wsh.springcloud.entity.Payment;
import com.wsh.springcloud.mapper.PaymentMapper;
import com.wsh.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentMapper paymentMapper;

    public int save(Payment payment) {
        return paymentMapper.save(payment);
    }

    public Payment getPaymentById(Long id) {
        return paymentMapper.getPaymentById(id);
    }
}
