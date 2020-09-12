package com.wsh.springcloud.alibaba.feign;

import com.wsh.springcloud.alibaba.feign.fallback.StorageFeignClientFallback;
import com.wsh.springcloud.common.JsonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 库存Feign远程服务调用接口
 * @Date 2020/9/11 20:17
 * @Author weishihuai
 * 说明:
 */
@FeignClient(value = "springcloudalibaba-seata-storage-service", fallback = StorageFeignClientFallback.class)
public interface StorageFeignClient {
    /**
     * 扣减库存方法
     *
     * @param productId
     * @param count
     * @return
     */
    @PostMapping(value = "/storage/decreaseStorage")
    Object decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
