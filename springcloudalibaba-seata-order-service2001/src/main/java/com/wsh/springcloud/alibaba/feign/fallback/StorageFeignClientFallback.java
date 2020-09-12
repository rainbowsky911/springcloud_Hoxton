package com.wsh.springcloud.alibaba.feign.fallback;

import com.wsh.springcloud.alibaba.feign.StorageFeignClient;
import com.wsh.springcloud.common.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class StorageFeignClientFallback implements StorageFeignClient {
    private static Logger logger = LoggerFactory.getLogger(StorageFeignClientFallback.class);

    @Override
    public JsonResult decreaseStorage(Long productId, Integer count) {
        logger.error("远程调用库存服务扣减库存[decreaseStorage]异常...");
        return null;
    }
}
