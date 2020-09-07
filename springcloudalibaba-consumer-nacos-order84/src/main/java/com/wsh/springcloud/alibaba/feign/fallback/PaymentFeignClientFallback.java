package com.wsh.springcloud.alibaba.feign.fallback;

import com.wsh.springcloud.alibaba.feign.PaymentFeignClient;
import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.stereotype.Component;

/**
 * @Description 远程调用失败回调类
 * @Date 2020/9/6 16:08
 * @Author weishihuai
 * 说明: 需实现FeignClient中定义的所有方法
 */
@Component
public class PaymentFeignClientFallback implements PaymentFeignClient {
    @Override
    public Object payment(Long id) {
        return new JsonResult<>(500, "服务降级返回,---PaymentFallbackService", new Payment(id, "errorSerial"));
    }
}
