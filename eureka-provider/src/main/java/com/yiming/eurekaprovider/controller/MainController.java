package com.yiming.eurekaprovider.controller;

import com.yiming.eurekaprovider.entity.Person;
import com.yiming.eurekaprovider.service.impl.HealthStatusServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    HealthStatusServiceImpl healthStatusServiceImpl;

    @GetMapping("/hi")
    public String getHi() {
        return "hi~ I'm a provider for Eureka";
    }

    @GetMapping("/client")
    public String client() {
        List<String> services = discoveryClient.getServices();
        for (String s : services) {
            System.out.println(s);
        }
        int order = discoveryClient.getOrder();
        System.out.println("order:" + order);
        List<ServiceInstance> instances = discoveryClient.getInstances("eureka-provider");
        for (ServiceInstance s : instances) {
            System.out.println(s);
        }
        return "hi";
    }

    @GetMapping("/health")
    public Health getHealth(@RequestParam("status") Boolean status) {
        healthStatusServiceImpl.setStatus(status);
        return healthStatusServiceImpl.health();
    }

    @GetMapping("/map")
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        map.put("hanser man", "YiMing");
        return map;
    }

    @PostMapping("/person")
    public Person getPerson(@RequestBody Person person) {
        person.setName(person.getName() + "~ SHUAI");
        return person;
    }

    @PostMapping("/postParam")
    public URI postURI(@RequestBody Person person, HttpServletResponse response) throws URISyntaxException {
        URI uri = new URI("https://www.baidu.com/s?wd=" + person.getName());
        response.addHeader("Location", uri.toString());
        return uri;
    }
}
