package com.yiming.userconsumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/22 15:56
 */
@Configuration
public class RestTemplateConfiguration {

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
