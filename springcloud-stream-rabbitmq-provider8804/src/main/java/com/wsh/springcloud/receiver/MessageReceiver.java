package com.wsh.springcloud.receiver;

import com.wsh.springcloud.channel.CustomMessageChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

/**
 * @Description:
 * @author: weishihuai
 * @Date: 2020/8/28 17:38
 * <p>
 * 说明：
 * 1. 在完成了消息通道绑定的定义后，这些用于定义绑定消息通道的接口则可以被 @EnableBinding 注解的 value 参数指定，从而在应用启动的时候实现对定义消息通道的绑定，
 * Spring Cloud Stream 会为其创建具体的实例，而开发者只需要通过注入的方式来获取这些实例并直接使用即可。
 * 2. @EnableBinding 注解用来指定一个或多个定义了 @Input 或 @Output 注解的接口，以此实现对消息通道（Channel）的绑定。通过 @EnableBinding(value = {CustomMessageChannel.class})
 * 绑定了 CustomMessageChannel 接口，该接口是我们自己实现的对输入输出消息通道绑定的定义.
 * 3. @StreamListener，主要定义在方法上，作用是将被修饰的方法注册为消息中间件上数据流的事件监听器，注解中的属性值对应了监听的消息通道名。将 receiveMsg 方法注册为 customInput
 * 消息通道的监听处理器，当我们往这个消息通道发送信息的时候，receiveMsg方法会执行。
 */
@EnableBinding(value = {CustomMessageChannel.class})
public class MessageReceiver {
    private Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @StreamListener(CustomMessageChannel.INPUT)
    public void receiveMsg(String message) {
        logger.info("消息接收方接收消息: {}", message);
    }

}
