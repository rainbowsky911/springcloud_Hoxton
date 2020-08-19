package com.wsh.springcloud.controller;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import com.wsh.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

//    @Resource
//    private DiscoveryClient discoveryClient;

    @PostMapping(value = "/payment/save")
    public JsonResult save(@RequestBody Payment payment) {
        int result = paymentService.save(payment);
        logger.info("*****插入结果：" + result);

        if (result > 0) {
            return new JsonResult(200, "插入数据库成功,serverPort: " + serverPort, result);
        } else {
            return new JsonResult(444, "插入数据库失败", null);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public JsonResult<Payment> getPaymentById(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new JsonResult(200, "查询成功,serverPort:  " + serverPort, payment);
        } else {
            return new JsonResult(444, "没有对应记录,查询ID: " + id, null);
        }
    }

//    @GetMapping(value = "/payment/discovery")
//    public Object discovery() {
//        List<String> services = discoveryClient.getServices();
//        for (String element : services) {
//            logger.info("*****element: " + element);
//        }
//
//        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");
//        for (ServiceInstance instance : instances) {
//            logger.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
//        }
//
//        return this.discoveryClient;
//    }

    @GetMapping(value = "/payment/customLoadBalancer")
    public String customLoadBalancer() {
        return "当前服务实例端口号：" + serverPort;
    }

    @GetMapping(value = "/payment/feign/timeout")
    public String paymentFeignTimeout() {
        // 业务逻辑处理正确，但是需要耗费3秒钟
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin() {
        return "hi ,i'am paymentzipkin server fall back，welcome to atguigu，O(∩_∩)O哈哈~";
    }

    /**
     * 测试gateway网关转发负载均衡转发
     */
    @GetMapping("/gatewayLoadBalance/{name}")
    public String gatewayLoadBalance(@PathVariable("name") String name) {
        return "hello, [gatewayLoadBalance] the name is :" + name + ", the server port is " + serverPort;
    }

}
