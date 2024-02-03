package com.dubs.core.server.dto;

import lombok.Data;

@Data
public class HealthStats {
    private int sysBp =  140;
    private int diaBp =  100;
    private float chol = 6.0f;
    // exercise time in minutes
    private int exerciseTime = 120;
}
