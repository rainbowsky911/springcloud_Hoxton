package com.wsh.springcloud.service;

import com.wsh.springcloud.entity.Payment;

public interface PaymentService {
    int save(Payment payment);

    Payment getPaymentById(Long id);
}
