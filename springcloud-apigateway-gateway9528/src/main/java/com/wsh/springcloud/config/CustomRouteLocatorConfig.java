package com.wsh.springcloud.config;

import com.wsh.springcloud.filter.CustomRequestTimeFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.config.CustomRouteLocatorConfig.java
 * @Description: 自定义路由定位器配置
 * @author: weishihuai
 * @date: 2020/8/21 15:41
 */
@Configuration
public class CustomRouteLocatorConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder routeLocatorBuilder) {
        RouteLocatorBuilder.Builder routes = routeLocatorBuilder.routes();
        routes.route("payment8001_customRequestTimeFilter",     //路由ID
                r -> r.path("/customRequestTimeFilter/**")  //路由路径正则表达式
                        .filters(f -> f.filter(new CustomRequestTimeFilter())  //自定义请求时间记录过滤器
                                .addRequestParameter("name", "weishihuai"))   //添加请求参数
                        .uri("http://localhost:8001")   //跳转目标服务地址
                        .order(0)
        ).build();
        return routes.build();
    }

}
