package com.yiming.userproviderconfigcenter.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/23 11:26
 */
@RestController
@RefreshScope
public class MyController {

    @Value("${my.config}")
    String MyConfig;

    @GetMapping
    public String getMyConfig() {
        return MyConfig;
    }

}
