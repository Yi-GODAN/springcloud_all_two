package com.yiming.userproviderconfigcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserProviderConfigCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProviderConfigCenterApplication.class, args);
    }

}