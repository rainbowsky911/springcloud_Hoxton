package com.wsh.springcloud.channel;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.channel.MessageChannel.java
 * @Description: 自定义消息通道
 * @author: weishihuai
 * @date: 2020/8/28 17:33
 * <p>
 * 说明:
 * 1. 创建 CustomMessageChannel 接口，通过 @Input和 @Output注解定义输入通道和输出通道，另外，@Input 和 @Output 注解都还有一个 value 属性，
 * 该属性可以用来设置消息通道的名称，这里指定的消息通道名称分别是 customInput 和 customOutput。如果直接使用两个注解而没有指定具体的 value 值，
 * 则会默认使用方法名作为消息通道的名称。
 * 2. 当定义输出通道的时候，需要返回 MessageChannel 接口对象，该接口定义了向消息通道发送消息的方法；
 * 定义输入通道时，需要返回 SubscribableChannel 接口对象，该接口继承自 MessageChannel 接口，它定义了维护消息通道订阅者的方法
 */

public interface CustomMessageChannel {
    String INPUT = "customInput";
    String OUTPUT = "customOutput";

    @Input(CustomMessageChannel.INPUT)
    SubscribableChannel input();

    @Output(CustomMessageChannel.OUTPUT)
    MessageChannel output();

}
