package com.wsh.springcloud.controller;

import com.wsh.springcloud.service.IMessageProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 消息发送测试Controller
 * @Date 2020/8/27 21:39
 * @Author weishihuai
 * 说明:
 */
@RestController
public class SendMessageController {
    @Resource
    private IMessageProvider messageProvider;

    @GetMapping(value = "/sendMessage")
    public String sendMessage() {
        return messageProvider.sendMessage();
    }

}
