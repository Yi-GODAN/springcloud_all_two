package com.yiming.userconsumer.api;

import com.yiming.userapi.api.RegisterApi;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 13:59
 */
@FeignClient(value = "user-provider", fallback = ConsumerBack.class)
public interface ConsumerApi extends RegisterApi {

    @RequestMapping(value = "/alive", method = RequestMethod.GET)
    String alive();

}
