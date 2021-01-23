package com.yiming.userconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 15:50
 */
//@Service
public class UserConsumerService {

//    @Autowired
//    RestTemplate restTemplate;
//
//    @HystrixCommand(fallbackMethod = "back")
//    public String alive() {
//        String url = "http://user-provider/alive";
//        return restTemplate.getForObject(url, String.class);
//    }
//
//    public String back() {
//        return "hi~ 请求失败";
//    }

}
