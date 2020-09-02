package com.wsh.springcloud.alibaba.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {

    @GetMapping("/sentinel")
    public String sentinel() {
//        try {
//            TimeUnit.MILLISECONDS.sleep(800);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        return "hello, sentinel dashboard....";
    }

    @GetMapping("/sentinel2")
    public String sentinel2() {
        return "hello, sentinel dashboard....";
    }

}
