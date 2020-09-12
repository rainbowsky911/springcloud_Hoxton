package com.wsh.springcloud.alibaba.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Mybatis配置类： 指定mapper接口包扫描路径
 * @Date 2020/9/11 20:30
 * @Author weishihuai
 * 说明:
 */
@Configuration
@MapperScan({"com.wsh.springcloud.alibaba.mapper"})
public class MyBatisConfig {
}
