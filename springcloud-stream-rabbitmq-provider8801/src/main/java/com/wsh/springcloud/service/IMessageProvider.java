package com.wsh.springcloud.service;

/**
 * @Description 消息发送接口
 * @Date 2020/8/27 21:37
 * @Author weishihuai
 * 说明:
 */
public interface IMessageProvider {
    /**
     * 发送消息
     */
    String sendMessage();
}
