package com.wsh.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 过滤器失败回调Controller
 * @author: weishihuai
 * @Date: 2020/8/21 10:50
 */
@RestController
public class GatewayFallbackController {

    @GetMapping("/fallback")
    public String gatewayFallback() {
        return "sorry, gateway service is busy,please try again later!";
    }

}
