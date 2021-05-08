package com.wsh.springcloud.alibaba.service;

import com.wsh.springcloud.alibaba.domain.Account;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

public interface AccountService {

    /**
     * 扣减账户余额
     *
     * @param userId 用户id
     * @param money  金额
     */
    void decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);

    /**
     *
     * @param id
     * @return
     */
    Account getById(Long id);
}
