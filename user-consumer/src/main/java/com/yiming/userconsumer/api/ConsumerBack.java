package com.yiming.userconsumer.api;

import org.springframework.stereotype.Component;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 16:05
 */
//@Component
public class ConsumerBack implements ConsumerApi{

    @Override
    public String alive() {
        return "it's down to level";
    }
}
