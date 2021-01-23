package com.yiming.eurekaconsumer.controller;

import com.yiming.eurekaconsumer.config.MyConfiguration;
import com.yiming.eurekaconsumer.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/18 19:09
 */
@RestController
public class BalanceController {

    @Autowired
    private MyConfiguration configuration;

    @GetMapping("/port")
    public String port() {
        return "port number is " + configuration.port;
    }

    @GetMapping("/balance")
    public String balance() {
        String url = "http://consumer/port";
        RestTemplate restTemplate = configuration.getLoadBalanced();
        return restTemplate.getForObject(url, String.class);
    }

    @GetMapping("/getMap")
    public ResponseEntity<Map> getMapBalance() {
        String url = "http://provider/map";
        RestTemplate restTemplate = configuration.getLoadBalanced();
        return restTemplate.getForEntity(url, Map.class);
    }

    @GetMapping("/getPerson")
    public ResponseEntity<Person> getPerson(@RequestBody Person person) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", person.getName());
        map.put("age", person.getAge());
        String url = "http://provider/person";
        RestTemplate restTemplate = configuration.getLoadBalanced();
        return restTemplate.postForEntity(url, map, Person.class);
    }

    @GetMapping("/location")
    public String getLocation(HttpServletResponse response) {
        String url = "http://provider/postParam";
        Map<String, String> map = Collections.singletonMap("name", "github");
        RestTemplate restTemplate = configuration.getLoadBalanced();
        URI location = restTemplate.postForLocation(url, map, Person.class);
        System.out.println(location);
        try {
            response.sendRedirect(location.toURL().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
