package com.wsh.springcloud.alibaba.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RefreshScope: 表示支持Nacos的动态刷新功能
@RefreshScope
public class NacosConfigServerController {
    @Value("${nacos.config.info}")
    private String configInfo;

    @GetMapping("/nacos/config/info")
    public String getConfigInfo() {
        return configInfo;
    }
}
