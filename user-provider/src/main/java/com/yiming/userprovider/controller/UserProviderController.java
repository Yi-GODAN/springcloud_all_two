package com.yiming.userprovider.controller;

import com.yiming.userapi.api.RegisterApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 14:06
 */
@RestController
public class UserProviderController implements RegisterApi {

    @Value("${server.port}")
    String port;

    private AtomicInteger count = new AtomicInteger(1);

    @Override
    public String alive() {

        try {
            System.out.println("准备睡");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int i = count.getAndIncrement();
        System.out.println("好的第" + i + "次调用");
        return "port: " + port;
    }
}
