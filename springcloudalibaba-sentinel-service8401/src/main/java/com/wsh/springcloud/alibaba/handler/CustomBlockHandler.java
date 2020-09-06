package com.wsh.springcloud.alibaba.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wsh.springcloud.common.JsonResult;

/**
 * @Description 自定义BlockHandler限流处理类
 * @Date 2020/9/6 14:52
 * @Author weishihuai
 * 说明: 必须是static静态方法
 */
public class CustomBlockHandler {

    public static JsonResult customBlockHandlerMethodA(BlockException exception) {
        return new JsonResult(500, "[客户自定义限流处理逻辑]--->customBlockHandlerMethodA");
    }

    public static JsonResult customBlockHandlerMethodB(BlockException exception) {
        return new JsonResult(500, "[客户自定义限流处理逻辑]--->customBlockHandlerMethodB");
    }
}
