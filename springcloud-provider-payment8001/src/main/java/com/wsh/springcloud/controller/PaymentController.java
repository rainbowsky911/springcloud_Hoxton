package com.wsh.springcloud.controller;

import com.wsh.springcloud.common.JsonResult;
import com.wsh.springcloud.entity.Payment;
import com.wsh.springcloud.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

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

    @GetMapping(value = "/payment/discovery")
    public Object discovery() {
        //获取Eureka上面注册的服务列表
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            logger.info("service: " + service);
        }

        //获取某个具体微服务的所有实例
        List<ServiceInstance> instances = discoveryClient.getInstances("SPRINGCLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance : instances) {
            logger.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort() + "\t" + instance.getUri());
        }
        return this.discoveryClient;
    }

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
     * 测试gateway网关转发请求简单示例
     */
    @GetMapping("/gatewaySimple/{name}")
    public String testGateway(@PathVariable("name") String name) {
        return "hello, the name is :" + name;
    }

    /**
     * 测试gateway网关转发请求简单示例 - RouteLocator方式
     */
    @GetMapping("/gatewaySimpleRouteLocator/{name}")
    public String gatewaySimpleRouteLocator(@PathVariable("name") String name) {
        return "hello, [RouteLocator] the name is :" + name;
    }

    /**
     * 测试gateway网关转发负载均衡转发
     */
    @GetMapping("/gatewayLoadBalance/{name}")
    public String gatewayLoadBalance(@PathVariable("name") String name) {
        return "hello, [gatewayLoadBalance] the name is :" + name + ", the server port is " + serverPort;
    }

    /**
     * 测试gateway网关After断言
     */
    @GetMapping("/gatewayAfterRoutePredicate/{name}")
    public String gatewayAfterRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayAfterRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Before断言
     */
    @GetMapping("/gatewayBeforeRoutePredicate/{name}")
    public String gatewayBeforeRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayBeforeRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Between断言
     */
    @GetMapping("/gatewayBetweenRoutePredicate/{name}")
    public String gatewayBetweenRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayBetweenRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Cookie断言
     */
    @GetMapping("/gatewayCookieRoutePredicate/{name}")
    public String gatewayCookieRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayCookieRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Header断言
     */
    @GetMapping("/gatewayHeaderRoutePredicate/{name}")
    public String gatewayHeaderRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayHeaderRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Host断言
     */
    @GetMapping("/gatewayHostRoutePredicate/{name}")
    public String gatewayHostRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayHostRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Method断言
     */
    @GetMapping("/gatewayMethodRoutePredicate/{name}")
    public String gatewayMethodRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayMethodRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Query断言
     */
    @GetMapping("/gatewayQueryRoutePredicate/{name}")
    public String gatewayQueryRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayQueryRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关RemoteAddr断言
     */
    @GetMapping("/gatewayRemoteAddrRoutePredicate/{name}")
    public String gatewayRemoteAddrRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayRemoteAddrRoutePredicate] the name is :" + name;
    }

    /**
     * 测试gateway网关Weight断言
     */
    @GetMapping("/gatewayWeightRoutePredicate/{name}")
    public String gatewayWeightRoutePredicate(@PathVariable("name") String name) {
        return "hello, [gatewayWeightRoutePredicate] the name is :" + name + ", the server port is " + serverPort;
    }

    /**
     * 测试gateway网关截取请求
     */
    @GetMapping("/gatewayStripPrefix/{name}")
    public String gatewayStripPrefix(@PathVariable("name") String name) {
        return "hello, [gatewayStripPrefix] the name is :" + name + ", the server port is " + serverPort;
    }

    /**
     * 测试gateway网关转发指定地址并添加请求参数
     */
    @GetMapping("/gatewayAddRequestParameter")
    public String gatewayAddRequestParameter(HttpServletRequest request) {
        String name = request.getParameter("name");
        return "hello, [gatewayAddRequestParameter] the name is :" + name;
    }

    /**
     * 测试gateway网关转发指定地址并传入参数
     */
    @GetMapping("/gatewayAddRequestHeader")
    public String gatewayAddRequestHeader(HttpServletRequest request) {
        String value = request.getHeader("X-Request-red");
        return "hello, [gatewayAddRequestHeader] the header[X-Request-red] is :" + value;
    }

    /**
     * 测试gateway网关转发指定地址并添加响应头
     */
    @GetMapping("/gatewayAddResponseHeader")
    public String gatewayAddResponseHeader() {
        return "hello, [gatewayAddResponseHeader] ";
    }

    /**
     * 测试gateway网关断路器过滤器
     */
    @GetMapping("/gatewayHystrixGatewayFilter")
    public String gatewayHystrixGatewayFilter() {
        return "hello, [gatewayHystrixGatewayFilter] ";
    }

    /**
     * 外部网关失败回调
     */
    @GetMapping("/gatewayFallback")
    public String gatewayFallback() {
        return "sorry,this is outer gateway fallback ";
    }

    /**
     * 外部网关失败回调
     */
    @GetMapping("/gatewayOuterFallbackHystrixGatewayFilter")
    public String gatewayOuterFallbackHystrixGatewayFilter() throws InterruptedException {
        //手动触发网关超时熔断
        TimeUnit.SECONDS.sleep(10);
        return "hello,[gatewayOuterFallbackHystrixGatewayFilter]";
    }

    /**
     * 测试路径前缀网关过滤器工厂
     */
    @GetMapping("/api/prefixPathGatewayFilter")
    public String prefixPathGatewayFilter() {
        return "hello,[prefixPathGatewayFilter]";
    }

    /**
     * 测试路径重写网关过滤器工厂
     */
    @GetMapping("/rewritePathGatewayFilter")
    public String rewritePathGatewayFilter() {
        return "hello, [rewritePathGatewayFilter]";
    }

}
