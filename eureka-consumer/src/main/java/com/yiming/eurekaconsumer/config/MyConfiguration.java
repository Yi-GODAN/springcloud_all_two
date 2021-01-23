package com.yiming.eurekaconsumer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.ZoneAvoidanceRule;
import com.yiming.eurekaconsumer.interceptor.LoggingClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/18 19:16
 */
@Configuration
public class MyConfiguration {

    @Value("${server.port}")
    public int port;

    @Bean
    @LoadBalanced
    public RestTemplate getLoadBalanced() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getInterceptors().add(new LoggingClientHttpRequestInterceptor());
        return restTemplate;
    }

    @Bean
    public IRule myRule() {
        return new ZoneAvoidanceRule();
    }

}