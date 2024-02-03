package com.dubs.core.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PredictRequest {
    private int bp;
    private int chol;
    private double bmi;
    private int physicalActivity;
    private int sex;
    private int ageGroup;

}
