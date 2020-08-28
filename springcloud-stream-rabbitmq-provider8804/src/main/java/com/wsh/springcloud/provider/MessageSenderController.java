package com.wsh.springcloud.provider;

import com.wsh.springcloud.channel.CustomMessageChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSenderController {

    @Autowired
    private CustomMessageChannel customMessageChannel;

    @GetMapping("/sendMsg")
    public void sendMsg() {
        customMessageChannel.output().send(MessageBuilder.withPayload("Hello World...").build());
    }

}
