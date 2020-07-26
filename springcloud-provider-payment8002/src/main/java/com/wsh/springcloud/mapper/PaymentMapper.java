package com.wsh.springcloud.mapper;

import com.wsh.springcloud.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description Mapper持久层接口
 * @Date 2020/7/25 9:53
 * @Author weishihuai
 * 说明:
 */
@Mapper
public interface PaymentMapper {

    int save(Payment payment);

    Payment getPaymentById(@Param("pkid") Long pkid);

}
