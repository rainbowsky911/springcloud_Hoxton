package com.wsh.springcloud.filter;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version V1.0
 * @ClassName: com.wsh.springcloud.filter.IpWhitelistGlobalGatewayFilter.java
 * @Description: 自定义全局过滤器 - IP白名单过滤
 * @author: weishihuai
 * @date: 2020/8/22 12:03
 */
@Component
public class IpWhiteListGlobalGatewayFilter implements GlobalFilter, Ordered {

    private static final Logger logger = LoggerFactory.getLogger(IpWhiteListGlobalGatewayFilter.class);

    /**
     * 白名单配置集合
     * 此处为了演示方便,实际中需要采取配置的方式去配置白名单
     */
    private static List<String> whiteList = new ArrayList<>();

    static {
        //localhost
        whiteList.add("0:0:0:0:0:0:0:1");
        //127.0.0.1
        whiteList.add("127.0.0.1");
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取发起请求的客户端IP地址
        String ipAddress = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();

        //判断是否在配置的白名单中,存在则放行;不存在则拦截
        if (whiteList.contains(ipAddress)) {
            //获取token成功,执行下一个过滤器逻辑
            return chain.filter(exchange);
        } else {
            logger.error("IP地址：{}未在白名单中，禁止访问！", ipAddress);
            ServerHttpResponse response = exchange.getResponse();
            Map<String, Object> data = new HashMap<>();
            data.put("code", HttpStatus.NOT_ACCEPTABLE);
            data.put("msg", "当前IP地址未在白名单中,禁止访问！");
            byte[] datas = JSON.toJSONString(data).getBytes(StandardCharsets.UTF_8);
            DataBuffer buffer = response.bufferFactory().wrap(datas);
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
            return response.writeWith(Mono.just(buffer));
        }
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
