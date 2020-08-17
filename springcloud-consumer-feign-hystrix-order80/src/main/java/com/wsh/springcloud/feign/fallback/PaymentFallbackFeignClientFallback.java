package com.wsh.springcloud.feign.fallback;

import com.wsh.springcloud.feign.PaymentHystrixFeignClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Description 远程服务调用接口-失败回调类
 * @Date 2020/8/16 16:07
 * @Author weishihuai
 * 说明:
 */
@Component
public class PaymentFallbackFeignClientFallback implements PaymentHystrixFeignClient {
    private static final Logger logger = LoggerFactory.getLogger(PaymentFallbackFeignClientFallback.class);

    @Override
    public String paymentInfoSuccess(Integer id) {
        logger.error("远程服务调用【paymentInfoSuccess】失败！");
        return "sorry,[paymentInfoSuccess], the payment service is not available! \";";
    }

    @Override
    public String paymentInfoFail(Integer id) {
        logger.error("远程服务调用【paymentInfoFail】失败！");
        return "sorry,[paymentInfoFail], the payment service is not available! \";";
    }
}
