package com.dubs.core.server.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dubs.core.server.client.PredictionClient;
import com.dubs.core.server.dto.HealthStats;
import com.dubs.core.server.dto.PredictRequest;
import com.dubs.core.server.dto.PredictResponse;
import com.dubs.core.server.entity.UserProfile;

@Service
@Slf4j
public class PredictService {

    @Autowired
    private PredictionClient predictClient;

    public PredictRequest generatePredictRequest(UserProfile profile, HealthStats stats) {

        double bmi = profile.getWeight() / (profile.getHeight() * profile.getHeight());
        log.info("BMI :{}",bmi);

        Date currentDate = new Date();
        int age = getYearsDifference(profile.getDateOfBirth(), currentDate);
        int ageGroup;
        if (age < 25) {
            ageGroup = 1;
        } else if (age > 80) {
            ageGroup = 13;
        } else {
            ageGroup = age / 5 - 4;
        }
        log.info("Age: {} Group:", age,ageGroup);


        int sex;
        switch (profile.getGender()) {
            case MALE:
                sex = 1;
                break;
            default:
                sex = 0;
                break;
        }

        int highBp;
        if (stats.getSysBp() < 120 && stats.getDiaBp() <= 80) {
            highBp = 0;
        } else {
            highBp = 1;
        }

        log.info("sys/dias: {}/{} High BP? {}",stats.getSysBp(),stats.getDiaBp(), highBp);

        int highChol;
        if (stats.getChol() <= 5.2) {
            highChol = 0;
        } else {
            highChol = 1;
        }
        log.info("chol: {} High Chol? {}",stats.getChol(),highChol);

        int isActive;
        if (stats.getExerciseTime() < 150) {
            isActive = 0;
        } else {
            isActive = 1;
        }

        log.info("Is active? {}",isActive);

        return new PredictRequest(highBp,highChol,bmi,isActive,sex,ageGroup);

    }


    public String getPrediction(PredictRequest predictRequest) {
        PredictResponse prediction = predictClient.predictDiabetes(predictRequest);
        log.info(">>> Probability: {}", prediction);
        if (prediction.getProbability() == 0) {
            return "You are in generally good health.";
        } else {
            return "You are at risk of diabetes. Please schedule a diabetes health screening";
        }
    }

    public Map<String,String> generateIndicators(PredictRequest predictRequest, HealthStats healthStats){
        Map<String,String> indicators = new HashMap<>();
        if(predictRequest.getBp() == 1){
            indicators.put("Systolic Blood Pressure", Integer.toString(healthStats.getSysBp()));
            indicators.put("Diastolic Blood Pressure", Integer.toString(healthStats.getDiaBp()));
        };
        if(predictRequest.getBmi() > 25){
            indicators.put("BMI",Double.toString(predictRequest.getBmi()));
        };
        if(predictRequest.getChol() == 1){
            indicators.put("Cholesterol", Float.toString(healthStats.getChol()));
        };
        return indicators;
    }

    private static int getYearsDifference(Date startDate, Date endDate) {
        Calendar startCal = Calendar.getInstance();
        startCal.setTime(startDate);

        Calendar endCal = Calendar.getInstance();
        endCal.setTime(endDate);

        int startYear = startCal.get(Calendar.YEAR);
        int endYear = endCal.get(Calendar.YEAR);

        int yearDifference = endYear - startYear;

        if (startCal.get(Calendar.DAY_OF_YEAR) > endCal.get(Calendar.DAY_OF_YEAR)) {
            yearDifference--;
        }

        return yearDifference;
    }
}
