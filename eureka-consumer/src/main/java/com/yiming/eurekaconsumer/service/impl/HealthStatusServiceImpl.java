package com.yiming.eurekaconsumer.service.impl;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Service;

/**
 * @Program: springcloud_all_two
 * @Description:
 * @Author: YiMing
 * @Created: 2021/01/18 19:39
 */
@Service
public class HealthStatusServiceImpl implements HealthIndicator {

    private Boolean status = true;

    @Override
    public Health health() {
        if (status) return new Health.Builder().up().build();
        return new Health.Builder().down().build();
    }

    public String getStatus() {
        return this.status.toString();
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
