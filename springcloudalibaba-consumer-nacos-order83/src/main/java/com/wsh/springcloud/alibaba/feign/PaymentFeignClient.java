package com.wsh.springcloud.alibaba.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: zdw
 * @Date: 2021/03/30/13:01
 * @Description:
 */

@FeignClient(value = "http://springcloudalibaba-provider-payment")
public interface PaymentFeignClient {

    @GetMapping(value = "/payment/nacos/{name}")
    public String getPayment(@PathVariable("name") String name);
}
