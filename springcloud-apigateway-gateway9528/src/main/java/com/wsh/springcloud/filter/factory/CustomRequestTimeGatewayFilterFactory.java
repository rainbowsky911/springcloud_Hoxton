package com.wsh.springcloud.filter.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * @Description 自定义请求时间过滤器工厂
 * @Date 2020/8/21 21:27
 * @Author weishihuai
 * 说明:
 */
public class CustomRequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<CustomRequestTimeGatewayFilterFactory.Config> {

    private static final Logger logger = LoggerFactory.getLogger(CustomRequestTimeGatewayFilterFactory.class);

    public static final String SHOULD_PARAMS = "shouldParams";

    public CustomRequestTimeGatewayFilterFactory() {
        super(CustomRequestTimeGatewayFilterFactory.Config.class);
    }

    public List<String> shortcutFieldOrder() {
        return Collections.singletonList(SHOULD_PARAMS);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String rawPath = exchange.getRequest().getURI().getRawPath();
            logger.info(rawPath + "-----------pre前置处理----------");
            long startTime = System.currentTimeMillis();
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        long endTime = System.currentTimeMillis();
                        logger.info(rawPath + "-----------post后置处理----------");
                        StringBuilder sb = new StringBuilder(rawPath)
                                .append("--------> 耗时: ")
                                .append(endTime - startTime)
                                .append("ms");
                        if (config.isShouldParams()) {
                            sb.append(",请求参数:").append(exchange.getRequest().getQueryParams());
                        }
                        logger.info(sb.toString());
                    })
            );
        };
    }

    public static class Config {
        /**
         * 是否输出参数
         */
        boolean shouldParams;

        public Config() {
        }

        public boolean isShouldParams() {
            return shouldParams;
        }

        public void setShouldParams(boolean shouldParams) {
            this.shouldParams = shouldParams;
        }
    }

}
