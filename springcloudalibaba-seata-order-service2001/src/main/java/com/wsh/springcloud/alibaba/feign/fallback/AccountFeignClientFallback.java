package com.wsh.springcloud.alibaba.feign.fallback;

import com.wsh.springcloud.alibaba.feign.AccountFeignClient;
import com.wsh.springcloud.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountFeignClientFallback implements AccountFeignClient {

    private static Logger logger = LoggerFactory.getLogger(AccountFeignClientFallback.class);

    @Override
    public JsonResult decreaseAccount(Long userId, BigDecimal money) {
        logger.error("远程调用账户服务扣减余额[decreaseAccount]异常...");
        return null;
    }

    @Override
    public Object getAccount(Long id) {
        logger.error("远程调用账户服务查询详情异常...");
        return null;
    }
}
