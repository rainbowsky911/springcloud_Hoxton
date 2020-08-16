package com.wsh.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description Feign日志增强配置
 * @Date 2020/8/16 9:05
 * @Author weishihuai
 * 说明:
 * Feign的日志级别主要有四种：
 * NONE：默认的，不显示任何日志；
 * BASIC：仅记录请求方法、URL、响应状态码以及执行时间；
 * HEADERS：处理BASIC中定义的信息外，还有请求和响应头信息；
 * FULL：处理HEADERS中定义的信息之外，还有请求和响应的正文以及元数据信息；
 */
@Configuration
public class FeignLogConfig {

    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.FULL;
    }

}
