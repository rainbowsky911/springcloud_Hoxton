package com.wsh.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
@DefaultProperties(defaultFallback = "commonDefaultFallback")
public class PaymentService {

    public String paymentInfoSuccess(Integer id) {
        return "线程名称:  " + Thread.currentThread().getName() + ">>>>id:  " + id;
    }

    /**
     * HystrixCommand注解主要开启熔断器的功能，并指定fallbackMethod熔断方法(服务不可用时执行熔断方法)
     * fallbackMethod: 指定服务降级方法
     * commandProperties: 指定断路器属性，如下配置在三秒内正常访问，超过三秒将会执行降级方法paymentInfoFailFallback
     * 说明：服务提供方降级处理，设置自身调用超时时间的峰值，峰值内可以正常运行，超过了需要有降级方法处理，做服务降级Fallback
     */
    @HystrixCommand(fallbackMethod = "paymentInfoFailFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000")
    })
    public String paymentInfoFail(Integer id) {
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程名称:  " + Thread.currentThread().getName() + ">>>>id:  " + id;
    }

//    @HystrixCommand
//    public String paymentInfoFail(Integer id) {
//        String string = null;
//        System.out.println(string.length());
//        return "线程名称:  " + Thread.currentThread().getName() + ">>>>id:  " + id;
//    }

    /**
     * Hystrix熔断方法（即调用失败回调方法）
     */
    public String paymentInfoFailFallback(Integer id) {
        return "sorry,[ " + id + "], the hystrix payment service is not available! ";
    }

    /**
     * 全局降级方法统一配置
     */
    public String commonDefaultFallback() {
        return "[全局降级配置], sorry, the system is busy, please try again later!";
    }


    /**
     * 测试服务熔断
     * 这里配置的几个参数表示： 10秒内请求10次数，如果超过60%的请求都是失败的，那么断路器将会开启.
     */
    @HystrixCommand(fallbackMethod = "testCircuitBreakerFallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
    })
    public String testCircuitBreaker(@PathVariable("id") Integer id) {
        if (id < 0) {
            //抛出运行时异常，会调用服务降级方法testCircuitBreakerFallback
            throw new RuntimeException("id不能为负数");
        }
        return "ID: " + UUID.randomUUID().toString();
    }

    public String testCircuitBreakerFallback(@PathVariable("id") Integer id) {
        return "testCircuitBreaker【服务降级处理】，ID: " + id;
    }

}
