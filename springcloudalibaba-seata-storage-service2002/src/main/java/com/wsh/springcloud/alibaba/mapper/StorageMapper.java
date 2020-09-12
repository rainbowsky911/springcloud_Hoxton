package com.wsh.springcloud.alibaba.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StorageMapper {

    /**
     * 扣减库存
     *
     * @param productId
     * @param count
     */
    void decreaseStorage(@Param("productId") Long productId, @Param("count") Integer count);
}
