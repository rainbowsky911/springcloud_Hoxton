package com.wsh.springcloud.loadbalance;

import org.springframework.cloud.client.ServiceInstance;

import java.util.List;

/**
 * @Description 自定义LoadBalancer
 * @Date 2020/8/4 16:13
 * @Author weishihuai
 * 说明:
 */
public interface CustomLoadBalancer {
    /**
     * 从Eureka服务注册列表中获取某个服务实例
     *
     * @param serviceInstanceList 服务注册列表
     * @return
     */
    ServiceInstance getInstances(List<ServiceInstance> serviceInstanceList);

}
