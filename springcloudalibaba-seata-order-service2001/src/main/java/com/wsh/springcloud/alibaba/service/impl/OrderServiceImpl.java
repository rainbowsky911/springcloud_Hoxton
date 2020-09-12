package com.wsh.springcloud.alibaba.service.impl;

import com.wsh.springcloud.alibaba.domain.Order;
import com.wsh.springcloud.alibaba.feign.AccountFeignClient;
import com.wsh.springcloud.alibaba.feign.StorageFeignClient;
import com.wsh.springcloud.alibaba.mapper.OrderMapper;
import com.wsh.springcloud.alibaba.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @Description 订单业务层实现类
 * @Date 2020/9/11 20:37
 * @Author weishihuai
 * 说明:
 */
@Service
public class OrderServiceImpl implements OrderService {
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Resource
    private OrderMapper orderMapper;
    @Resource
    private StorageFeignClient storageFeignClient;
    @Resource
    private AccountFeignClient accountFeignClient;

    /**
     * 订单服务创建订单->调用库存服务扣减库存->调用账户服务扣减账户余额->修改订单状态
     */
    @Override
    @GlobalTransactional(name = "wsh-create-order", rollbackFor = Exception.class)
    public void createOrder(Order order) {
        logger.info("创建订单[createOrder] start....." + new Date());
        //1 新建订单
        orderMapper.createOrder(order);
        logger.info("创建订单[createOrder] end....." + new Date());

        //2 扣减库存
        logger.info("调用库存服务扣减库存[decreaseStorage] start....." + new Date());
        storageFeignClient.decreaseStorage(order.getProductId(), order.getCount());
        logger.info("调用库存服务扣减库存[decreaseStorage] end....." + new Date());

        //3 扣减账户
        logger.info("调用账户服务扣减余额[decreaseAccount] start....." + new Date());
        accountFeignClient.decreaseAccount(order.getUserId(), order.getMoney());
        logger.info("调用账户服务扣减余额[decreaseAccount] end....." + new Date());

        //4 修改订单状态，从零到1, 1代表已经完成
        logger.info("修改订单状态[updateOrderStatus] start....." + new Date());
        orderMapper.updateOrderStatus(order.getUserId(), 0);
        logger.info("修改订单状态[updateOrderStatus] end....." + new Date());
    }
}
