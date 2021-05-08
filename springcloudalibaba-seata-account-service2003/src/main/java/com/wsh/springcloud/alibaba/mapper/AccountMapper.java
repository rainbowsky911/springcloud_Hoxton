package com.wsh.springcloud.alibaba.mapper;

import com.wsh.springcloud.alibaba.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

@Mapper
public interface AccountMapper {

    /**
     * 扣减账户余额
     */
    void decreaseAccount(@Param("userId") Long userId, @Param("money") BigDecimal money);


    Account getById(Long id);
}
