package com.wsh.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 自定义ribbon配置类不能够放在@ComponentScan下扫描的包以及子包下.
 * @Date 2020/8/4 15:16
 * @Author weishihuai
 * 说明: 
 */
@Configuration
public class CustomRibbonConfig {

    @Bean
    public IRule customRule() {
        //定义为随机策略
        return new RandomRule();
    }

}
