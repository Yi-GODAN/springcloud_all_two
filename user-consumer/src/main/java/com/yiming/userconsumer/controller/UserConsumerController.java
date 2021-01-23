package com.yiming.userconsumer.controller;

import com.yiming.userconsumer.api.ConsumerApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 13:57
 */
@RestController
public class UserConsumerController {

    @Autowired
    ConsumerApi api;

    @Value("${server.port}")
    String port;

    @GetMapping("/alive")
    public String alive() {
        return api.alive();
    }

    @GetMapping("/port")
    public String getPort() {
        return "this consumer port: " + port;
    }
}
