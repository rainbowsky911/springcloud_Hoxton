package com.wsh.springcloud.controller;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import com.wsh.springcloud.loadbalance.CustomLoadBalancer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
public class OrderController {
    //支付服务提供者地址
//    public static final String PAYMENT_URL = "http://localhost:8001";

    //支付服务提供者注册到Eureka的application name
    public static final String PAYMENT_URL = "http://SPRINGCLOUD-PAYMENT-SERVICE";

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private CustomLoadBalancer customLoadBalancer;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/consumer/payment/save")
    public JsonResult<Payment> savePayment(@RequestBody Payment payment) {
        return restTemplate.postForObject(PAYMENT_URL + "/payment/save", payment, JsonResult.class);
    }

    @GetMapping("/consumer/payment/get/{id}")
    public JsonResult<Payment> getPayment(@PathVariable("id") Long id) {
        return restTemplate.getForObject(PAYMENT_URL + "/payment/get/" + id, JsonResult.class);
    }

    @GetMapping("/consumer/payment/getForEntity/{id}")
    public JsonResult<Payment> getPayment2(@PathVariable("id") Long id) {
        ResponseEntity<JsonResult> entity = restTemplate.getForEntity(PAYMENT_URL + "/payment/get/" + id, JsonResult.class);
        if (entity.getStatusCode().is2xxSuccessful()) {
            return entity.getBody();
        } else {
            return new JsonResult<>(444, "操作失败");
        }
    }

//    @GetMapping(value = "/consumer/payment/lb")
//    public String getPaymentLB() {
//        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
//
//        if (instances == null || instances.size() <= 0) {
//            return null;
//        }
//
//        ServiceInstance serviceInstance = loadBalancer.instances(instances);
//        URI uri = serviceInstance.getUri();
//
//        return restTemplate.getForObject(uri + "/payment/lb", String.class);
//
//    }

    // ====================> zipkin+sleuth
    @GetMapping("/consumer/payment/zipkin")
    public String paymentZipkin() {
        String result = restTemplate.getForObject("http://localhost:8001" + "/payment/zipkin/", String.class);
        return result;
    }

    @GetMapping("/consumer/payment/customLoadBalancer")
    public String customLoadBalancer() {
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");
        if (instances == null || instances.size() <= 0) {
            return null;
        }
        ServiceInstance serviceInstance = customLoadBalancer.getInstances(instances);
        //获取服务提供者的URI
        URI serviceInstanceUri = serviceInstance.getUri();
        return restTemplate.getForObject(serviceInstanceUri + "/payment/customLoadBalancer", String.class);
    }

}
