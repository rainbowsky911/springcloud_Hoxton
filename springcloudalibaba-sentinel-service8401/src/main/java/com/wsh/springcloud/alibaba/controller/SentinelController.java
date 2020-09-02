package com.wsh.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SentinelController {

    @GetMapping("/sentinel")
    public String sentinel() {
        return "hello, sentinel dashboard....";
    }

}
