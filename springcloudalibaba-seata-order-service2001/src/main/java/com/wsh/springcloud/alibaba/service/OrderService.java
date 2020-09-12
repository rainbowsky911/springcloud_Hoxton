package com.wsh.springcloud.alibaba.service;

import com.wsh.springcloud.alibaba.domain.Order;

/**
 * @Description 订单业务层接口
 * @Date 2020/9/11 20:13
 * @Author weishihuai
 * 说明:
 */
public interface OrderService {
    /**
     * 创建订单方法
     *
     * @param order
     */
    void createOrder(Order order);
}
