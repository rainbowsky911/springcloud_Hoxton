package com.wsh.springcloud.alibaba.controller;


import com.wsh.springcloud.alibaba.service.StorageService;
import com.wsh.springcloud.common.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    /**
     * 扣减库存
     */
    @RequestMapping("/storage/decreaseStorage")
    public JsonResult decreaseStorage(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        storageService.decreaseStorage(productId, count);
        return new JsonResult(200, "扣减库存成功......");
    }
}
