package com.dubs.core.server.service;

import com.dubs.core.server.dto.HealthIndicators;
import org.springframework.stereotype.Service;


@Service
public class IndicatorService {

    public HealthIndicators getIndicators(String message){
        HealthIndicators healthIndicators = new HealthIndicators();
        healthIndicators.setIndicator((message.split("-")));
        return healthIndicators;
    }
}
