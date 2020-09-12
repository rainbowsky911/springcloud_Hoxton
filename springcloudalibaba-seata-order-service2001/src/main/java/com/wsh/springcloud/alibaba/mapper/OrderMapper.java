package com.wsh.springcloud.alibaba.mapper;

import com.wsh.springcloud.alibaba.domain.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface OrderMapper {
    /**
     * 新建订单
     *
     * @param order
     */
    void createOrder(Order order);

    /**
     * 修改订单状态已完成
     *
     * @param userId
     * @param status
     */
    void updateOrderStatus(@Param("userId") Long userId, @Param("status") Integer status);
}