package com.wsh.springcloud.feign;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description Feign接口
 * @Date 2020/8/15 21:02
 * @Author weishihuai
 * 说明:
 * Feign是一个interface接口
 * Feign的实现的过程大致如下：
 * a. 首先通过@EnableFeignClients注解开启FeignClient
 * b. 根据Feign的规则实现接口，并加@FeignClient注解
 * c. 程序启动后，会进行包扫描，扫描所有的@ FeignClient的注解的类，并将这些信息注入到ioc容器中。
 * d. 当接口的方法被调用，通过jdk的代理，来生成具体的RequestTemplate
 * e. RequestTemplate在生成Request
 * f. Request交给Client去处理，其中Client可以是HttpUrlConnection、HttpClient也可以是Okhttp
 * g. 最后Client被封装到LoadBalanceClient类，这个类结合类Ribbon做到了负载均衡
 */
@Component
//@FeignClient注解value属性指定Payment服务注册到Eureka上的ApplicationName
//@FeignClient注解fallback属性指定失败回调
@FeignClient(value = "SPRINGCLOUD-PAYMENT-SERVICE")
public interface PaymentServiceFeignClient {

    @GetMapping(value = "/payment/get/{id}")
    JsonResult<Payment> getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/feign/timeout")
    String paymentFeignTimeout();

}
