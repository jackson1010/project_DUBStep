package com.dubs.core.server.dto;

import lombok.Data;

@Data
public class HealthStats {
    private int sysBp;
    private int diaBp;
    private float chol;
    // exercise time in minutes
    private int exerciseTime;
    
}
