package com.yiming.eurekaconsumer.controller;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.yiming.eurekaconsumer.service.impl.HealthStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Stream;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/18 11:35
 */
@RestController
public class MainController {

    @Autowired
    DiscoveryClient discoveryClient;

    @Qualifier("eurekaClient")
    @Autowired
    EurekaClient eurekaClient;

    @Autowired
    LoadBalancerClient lb;

    @GetMapping("/hi")
    public String getHi() {
        return "hi~ this is consumer for Eureka!";
    }

    @GetMapping("/client")
    public Object client() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            System.out.println(s);
        }
        int order = discoveryClient.getOrder();
        System.out.println("order:" + order);
        List<ServiceInstance> provider = discoveryClient.getInstances("provider");
        Stream.of(provider).forEach(System.out::println);
        List<InstanceInfo> provider1 = eurekaClient.getInstancesByVipAddress("provider", false);
        System.out.println(discoveryClient.description());
        if (provider1.size() > 0) {
            InstanceInfo instanceInfo = provider1.get(0);
            if (instanceInfo.getStatus() == InstanceInfo.InstanceStatus.UP) {
                String url = "http://" + instanceInfo.getHostName() + ":" + instanceInfo.getPort() + "/hi";
                System.out.println("url:" + url);
                RestTemplate restTemplate = new RestTemplate();
                String forObject = restTemplate.getForObject(url, String.class);
                System.out.println("respStr: " + forObject);
            }
        }
        ServiceInstance provider2 = lb.choose("provider");
        String url2 = provider2.getUri() + "/hi";
        RestTemplate restTemplate = new RestTemplate();
        String forObject = restTemplate.getForObject(url2, String.class);
        System.out.println("respStr2:" + forObject);
        return discoveryClient.getInstances("provider");
    }

    @Autowired
    HealthStatusServiceImpl healthStatusServiceImpl;

    @GetMapping("/health")
    public Health getHealth(@RequestParam("status") Boolean status) {
        healthStatusServiceImpl.setStatus(status);
        return healthStatusServiceImpl.health();
    }

}
