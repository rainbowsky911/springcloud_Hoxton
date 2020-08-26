package com.wsh.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Value("${config.server.info}")
    String message;

    @RequestMapping("/getPropertyFromConfigServer")
    public String getPropertyFromConfigServer() {
        String msg = "hello, i am " + message + ", i'm come from config server";
        logger.info(msg);
        return msg;
    }

}
