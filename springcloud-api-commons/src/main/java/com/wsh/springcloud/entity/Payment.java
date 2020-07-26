package com.wsh.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 支付实体类
 * @Date 2020/7/26 9:47
 * @Author weishihuai
 * 说明:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Long pkid;
    private String serial;
}