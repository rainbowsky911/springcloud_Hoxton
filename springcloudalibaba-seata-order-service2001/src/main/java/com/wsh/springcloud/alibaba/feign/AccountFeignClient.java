package com.wsh.springcloud.alibaba.feign;

import com.wsh.springcloud.alibaba.feign.fallback.AccountFeignClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

/**
 * @Description 账户Feign远程服务调用接口
 * @Date 2020/9/11 20:16
 * @Author weishihuai
 * 说明:
 */
@FeignClient(value = "springcloudalibaba-seata-account-service", fallback = AccountFeignClientFallback.class)
public interface AccountFeignClient {
    /**
     * 扣减用户余额方法
     *
     * @param userId
     * @param money
     * @return
     */
    @PostMapping(value = "/account/decreaseAccount")
    Object decreaseAccount(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);


    @GetMapping(value = "/{id}")
    Object getAccount(@PathVariable(value = "id") Long id);

}
