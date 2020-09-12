package com.wsh.springcloud.alibaba.controller;

import com.wsh.springcloud.alibaba.domain.Order;
import com.wsh.springcloud.alibaba.service.OrderService;
import com.wsh.springcloud.common.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description 订单控制层接口
 * @Date 2020/9/11 20:36
 * @Author weishihuai
 * 说明:
 */
@RestController
public class OrderController {
    @Resource
    private OrderService orderService;

    @GetMapping("/order/createOrder")
    public JsonResult createOrder(Order order) {
        orderService.createOrder(order);
        return new JsonResult(200, "订单创建成功.....");
    }

}
