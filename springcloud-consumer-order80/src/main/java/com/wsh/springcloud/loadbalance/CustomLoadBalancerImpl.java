package com.wsh.springcloud.loadbalance;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description 自定义LoadBalancer实现类
 * @Date 2020/8/4 16:13
 * @Author weishihuai
 * 说明:
 */
@Component
public class CustomLoadBalancerImpl implements CustomLoadBalancer {
    private static final Logger logger = LoggerFactory.getLogger(CustomLoadBalancerImpl.class);

    //原子整形类
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     *
     * @return
     */
    public final int getAndIncrement() {
        int current;
        //记录第几次访问
        int visitIndex;
        //自旋锁比较
        do {
            //获取当前值
            current = this.atomicInteger.get();
            visitIndex = current >= Integer.MAX_VALUE ? 0 : (current + 1);
        } while (!this.atomicInteger.compareAndSet(current, visitIndex)); //采用CAS算法保证原子操作
        logger.info("第几次访问次数 : {}", visitIndex);
        return visitIndex;
    }

    @Override
    public ServiceInstance getInstances(List<ServiceInstance> serviceInstanceList) {
        int index = getAndIncrement() % serviceInstanceList.size();
        return serviceInstanceList.get(index);
    }

}
