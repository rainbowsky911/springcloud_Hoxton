package com.wsh.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wsh.springcloud.alibaba.feign.PaymentFeignClient;
import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class CircleBreakerController {
    /**
     * 指定服务提供者的微服务名称
     */
    private static final String SERVICE_URL = "http://springcloudalibaba-provider-payment";

    /**
     * 负载均衡调用类
     */
    @Resource
    private RestTemplate restTemplate;

    /**
     * 没有配置fallback和blockHandler
     */
    @RequestMapping("/consumer/noFallback/{id}")
    @SentinelResource(value = "noFallback") //没有配置
    public JsonResult<Payment> noFallback(@PathVariable Long id) {
        JsonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, JsonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException，暂未找到对应记录......");
        }
        return result;
    }

    /**
     * fallback只负责业务异常
     */
    @RequestMapping("/consumer/fallback/{id}")
    @SentinelResource(value = "fallback", fallback = "handlerFallback")
    public JsonResult<Payment> fallback(@PathVariable Long id) {
        JsonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, JsonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录......");
        }
        return result;
    }

    /**
     * blockHandler只负责sentinel控制台配置的违规情况，业务运行时异常sentinel不管，该抛出还是抛出
     */
    @RequestMapping("/consumer/withBlockHandler/{id}")
    @SentinelResource(value = "withBlockHandler", blockHandler = "blockHandler")
    public JsonResult<Payment> withBlockHandler(@PathVariable Long id) {
        JsonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, JsonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录......");
        }
        return result;
    }

    /**
     * blockHandler只负责sentinel控制台配置的违规情况，业务运行时异常sentinel不管，该抛出还是抛出
     * fallback只负责业务异常
     */
    @RequestMapping("/consumer/withBlockHandlerAndFallback/{id}")
    @SentinelResource(value = "withBlockHandlerAndFallback", fallback = "handlerFallback", blockHandler = "blockHandler",
            exceptionsToIgnore = {IllegalArgumentException.class})
    public JsonResult<Payment> withBlockHandlerAndFallback(@PathVariable Long id) {
        JsonResult<Payment> result = restTemplate.getForObject(SERVICE_URL + "/payment/" + id, JsonResult.class, id);
        if (id == 4) {
            throw new IllegalArgumentException("IllegalArgumentException,非法参数异常......");
        } else if (result.getData() == null) {
            throw new NullPointerException("NullPointerException,该ID没有对应记录......");
        }
        return result;
    }

    /**
     * 业务异常兜底降级方法
     */
    public JsonResult handlerFallback(@PathVariable Long id, Throwable e) {
        Payment payment = new Payment(id, "null");
        return new JsonResult<>(444, "[业务异常兜底降级方法],exception内容:  " + e.getMessage(), payment);
    }

    /**
     * sentinel控制台配置违反兜底降级方法
     */
    public JsonResult blockHandler(@PathVariable Long id, BlockException blockException) {
        return new JsonResult<>(445, "[sentinel控制台配置违反兜底降级方法],无此流水: blockException  " + blockException.getMessage(), new Payment(id, "null"));
    }

    @Resource
    private PaymentFeignClient paymentFeignClient;

    @GetMapping(value = "/consumer/openfeign/payment/{id}")
    public JsonResult<Payment> payment(@PathVariable("id") Long id) {
        return paymentFeignClient.payment(id);
    }

}
