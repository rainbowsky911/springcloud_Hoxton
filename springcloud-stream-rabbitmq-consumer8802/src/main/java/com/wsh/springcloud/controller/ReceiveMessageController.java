package com.wsh.springcloud.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.controller.ReceiveMessageController.java
 * @Description: 接收消息发送方发送的消息
 * @author: weishihuai
 * @date: 2020/8/28 10:55
 */
@Component
@EnableBinding(Sink.class)
public class ReceiveMessageController {

    private static final Logger logger = LoggerFactory.getLogger(ReceiveMessageController.class);

    @Value("${server.port}")
    private String serverPort;

    /**
     * 接收消息发送方发送的消息
     *
     * @param message 消息
     * @StreamListener 通过@StreamListener注解来监听exchange中的消息
     */
    @StreamListener(Sink.INPUT)
    private void receiveMessage(Message<String> message) {
        String payload = message.getPayload();
        logger.info("消息接收方接收消息: {}, 服务端口号：{}", payload, serverPort);
    }

}
