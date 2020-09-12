package com.wsh.springcloud.alibaba.service;


public interface StorageService {
    /**
     * 扣减库存
     */
    void decreaseStorage(Long productId, Integer count);
}
