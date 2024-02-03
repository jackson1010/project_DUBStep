package com.dubs.core.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PredictRequest {
    private int bp;
    private int chol;
    private double bmi;
    private int physicalActivity;
    private int sex;
    private int ageGroup;

}
