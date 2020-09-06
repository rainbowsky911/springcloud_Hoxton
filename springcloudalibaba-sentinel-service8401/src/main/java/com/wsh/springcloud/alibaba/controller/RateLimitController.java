package com.wsh.springcloud.alibaba.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wsh.springcloud.alibaba.handler.CustomBlockHandler;
import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
public class RateLimitController {

    /**
     * 根据资源名称进行限流
     */
    @GetMapping("/limitByResource")
    @SentinelResource(value = "limitByResource", blockHandler = "blockHandlerLimitByResource")
    public JsonResult limitByResource() {
        return new JsonResult(200, "根据资源名称进行限流", new Payment(20200906L, UUID.randomUUID().toString()));
    }

    /**
     * 服务兜底降级方法
     */
    public JsonResult blockHandlerLimitByResource(BlockException exception) {
        return new JsonResult(500, exception.getClass().getCanonicalName() + "\t 服务不可用");
    }

    /**
     * 根据URL进行限流
     */
    @GetMapping("/limitByUrl")
    @SentinelResource(value = "limitByUrl")
    public JsonResult limitByUrl() {
        return new JsonResult(200, "根据URL进行限流", new Payment(20200906L, UUID.randomUUID().toString()));
    }

    /**
     * 客户自定义限流处理逻辑
     * blockHandlerClass: 指定限流逻辑处理类
     * blockHandler: 指定限流处理方法, 对应blockHandlerClass处理类中的方法名称
     */
    @GetMapping("/customBlockHandler")
    @SentinelResource(value = "customBlockHandler",
            blockHandlerClass = CustomBlockHandler.class,
            blockHandler = "customBlockHandlerMethodA")
    public JsonResult customerBlockHandler() {
        return new JsonResult(200, "客户自定义限流处理逻辑", new Payment(20200906L, UUID.randomUUID().toString()));
    }

}