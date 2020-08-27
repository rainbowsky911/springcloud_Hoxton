package com.wsh.springcloud.service.impl;

import com.wsh.springcloud.service.IMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description 消息发送实现类
 * @Date 2020/8/27 21:38
 * @Author weishihuai
 * 说明:
 */
//定义消息的发送管道
//指信道channel和exchange绑定在一起
@EnableBinding(Source.class)
public class MessageProviderImpl implements IMessageProvider {

    private static final Logger logger = LoggerFactory.getLogger(MessageProviderImpl.class);

    /**
     * 消息发送管道
     */
    @Resource
    private MessageChannel messageChannel;

    @Override
    public String sendMessage() {
        String uuid = UUID.randomUUID().toString();
        messageChannel.send(MessageBuilder.withPayload(uuid).build());
        logger.info("消息发送者发送消息: {}", uuid);
        return "消息发送者发送消息: " + uuid;
    }
}
