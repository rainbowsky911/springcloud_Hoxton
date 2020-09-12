package com.wsh.springcloud.alibaba.controller;

import com.wsh.springcloud.alibaba.service.AccountService;
import com.wsh.springcloud.common.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;

@RestController
public class AccountController {

    @Resource
    AccountService accountService;

    /**
     * 扣减账户余额
     */
    @RequestMapping("/account/decreaseAccount")
    public JsonResult decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money) {
        accountService.decreaseAccount(userId, money);
        return new JsonResult(200, "扣减账户余额成功！");
    }
}
