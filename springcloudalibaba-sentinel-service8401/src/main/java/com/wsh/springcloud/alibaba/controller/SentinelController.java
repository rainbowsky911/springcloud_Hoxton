package com.wsh.springcloud.alibaba.controller;

import com.wsh.springcloud.alibaba.service.SentinelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
public class SentinelController {

    private static Logger logger = LoggerFactory.getLogger(SentinelController.class);

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

    @GetMapping("/sentinel3")
    public String sentinel3() {
        logger.info(Thread.currentThread().getName() + ">>>>>>sentinel3");
        return "hello, sentinel dashboard....";
    }

    @Autowired
    private SentinelService sentinelService;

    @GetMapping("/testA")
    public String testA() {
        logger.info("SentinelController>>>>>testA() execute....");
        return sentinelService.sentinelChain();
    }

    @GetMapping("/testB")
    public String testB() {
        logger.info("SentinelController>>>>>testB() execute....");
        return sentinelService.sentinelChain();
    }

    @GetMapping("/testRt")
    public String testRt() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "testRt..";
    }

    @GetMapping("/testExceptionRate")
    public String testExceptionRate() {
        String string = null;
        logger.info(string.toString());
        return "testExceptionRate..";
    }


    @GetMapping("/testExceptionCount")
    public String testExceptionCount() {
        int i = 10 / 0;
        return "testExceptionRate..";
    }


}
