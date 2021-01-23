package com.yiming.userapi.api;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/20 14:58
 */
public interface RegisterApi {

    @GetMapping("/alive")
    String alive();

}