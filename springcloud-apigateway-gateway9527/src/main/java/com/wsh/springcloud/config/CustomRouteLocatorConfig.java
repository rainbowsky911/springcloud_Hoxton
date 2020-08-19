package com.wsh.springcloud.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.config.CustomRouteLocatorConfig.java
 * @Description: 自定义路由定位器配置
 * @author: weishihuai
 * @date: 2020/8/19 13:40
 */
@Configuration
public class CustomRouteLocatorConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("payment8001_gatewaySimpleRouteLocator",  //路由ID
                r -> r.path("/gatewaySimpleRouteLocator/**") //路由路径正则表达式
                        .uri("http://localhost:8001") //跳转目标服务地址
        ).build();
        return routes.build();
    }

}
