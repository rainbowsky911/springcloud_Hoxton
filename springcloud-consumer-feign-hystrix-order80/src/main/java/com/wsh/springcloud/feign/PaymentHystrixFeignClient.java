package com.wsh.springcloud.feign;

import com.wsh.springcloud.feign.fallback.PaymentFallbackFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description 远程服务调用接口
 * @Date 2020/8/16 16:06
 * @Author weishihuai
 * 说明:
 */
@Component
//fallback：指定失败回调类
@FeignClient(value = "SPRINGCLOUD-PROVIDER-HYSTRIX-PAYMENT", fallback = PaymentFallbackFeignClientFallback.class)
public interface PaymentHystrixFeignClient {

    @GetMapping("/payment/hystrix/success/{id}")
    String paymentInfoSuccess(@PathVariable("id") Integer id);

    @GetMapping("/payment/hystrix/fail/{id}")
    String paymentInfoFail(@PathVariable("id") Integer id);
}
