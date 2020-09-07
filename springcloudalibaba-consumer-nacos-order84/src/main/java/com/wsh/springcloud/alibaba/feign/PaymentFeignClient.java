package com.wsh.springcloud.alibaba.feign;

import com.wsh.springcloud.alibaba.feign.fallback.PaymentFeignClientFallback;
import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description Feign远程调用类
 * @Date 2020/9/6 16:07
 * @Author weishihuai
 * 说明:
 */
@FeignClient(value = "springcloudalibaba-provider-payment", fallback = PaymentFeignClientFallback.class)
public interface PaymentFeignClient {

    @GetMapping(value = "/payment/{id}")
    Object payment(@PathVariable("id") Long id);

}
