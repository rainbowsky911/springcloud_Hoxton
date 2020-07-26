package com.wsh.springcloud.common;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 统一返回结果包装类
 * @Date 2020/7/26 9:50
 * @Author weishihuai
 * 说明:
 */
@Data
@NoArgsConstructor
public class JsonResult<T> {

    /**
     * 状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public JsonResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public JsonResult(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
