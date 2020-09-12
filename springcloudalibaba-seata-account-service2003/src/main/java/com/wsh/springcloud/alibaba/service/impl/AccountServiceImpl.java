package com.wsh.springcloud.alibaba.service.impl;


import com.wsh.springcloud.alibaba.mapper.AccountMapper;
import com.wsh.springcloud.alibaba.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 账户业务实现类
 * Created by zzyy on 2019/11/11.
 */
@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Resource
    AccountMapper accountMapper;

    /**
     * 扣减账户余额
     */
    public void decreaseAccount(Long userId, BigDecimal money) {
        logger.info("账户服务扣减余额[decreaseAccount] start....." + new Date());

        //feign调用默认调用超时时间为1秒，这里模拟休眠20秒，肯定调用超时
        //模拟超时异常，全局事务回滚
        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        accountMapper.decreaseAccount(userId, money);
        logger.info("账户服务扣减余额[decreaseAccount] end....." + new Date());
    }
}
