package com.wsh.springcloud.alibaba.service.impl;

import com.wsh.springcloud.alibaba.mapper.StorageMapper;
import com.wsh.springcloud.alibaba.service.StorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

@Service
public class StorageServiceImpl implements StorageService {
    private static final Logger logger = LoggerFactory.getLogger(StorageServiceImpl.class);

    @Resource
    private StorageMapper storageMapper;

    /**
     * 扣减库存
     */
    @Override
    public void decreaseStorage(Long productId, Integer count) {
        logger.info("库存服务库存扣减[decreaseStorage] start....." + new Date());
        storageMapper.decreaseStorage(productId, count);
        logger.info("库存服务库存扣减[decreaseStorage] end....." + new Date());
    }

}
