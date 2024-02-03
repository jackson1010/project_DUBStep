package com.dubs.core.server.service;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dubs.core.server.client.PredictionClient;
import com.dubs.core.server.dto.HealthStats;
import com.dubs.core.server.dto.PredictRequest;
import com.dubs.core.server.dto.PredictResponse;
import com.dubs.core.server.entity.UserProfile;

@Service
public class PredictService {
    
    @Autowired
    private PredictionClient predictClient;

    public String getPrediction(UserProfile profile, HealthStats stats){

        PredictRequest predictRequest = new PredictRequest();

        // double bmi = profile.getWeight() / (profile.getHeight() * profile.getHeight()) ;
    

        // Date currentDate = new Date();
        // int age = getYearsDifference(profile.getDateOfBirth(), currentDate);
        // System.out.println("Age: " + age);
        // int ageGroup ;
        // if(age <25){
        //     ageGroup=1;
        // } else if (age <30){
        //     ageGroup=2;
        // } else if (age <35){
        //     ageGroup=3;
        // } else if (age <40){
        //     ageGroup=4;
        // } else if (age <45){
        //     ageGroup=5;
        // } else if (age <50){
        //     ageGroup=6;
        // } else if (age <55){
        //     ageGroup=7;
        // } else if (age <60){
        //     ageGroup=8;
        // } else if (age <65){
        //     ageGroup=9;
        // } else if (age <70){
        //     ageGroup=10;
        // } else if (age <75){
        //     ageGroup=11;
        // } else if (age <80){
        //     ageGroup=12;
        // } else {
        //     ageGroup=13;
        // }
        
        // int sex;

        // switch (profile.getGender()) {
        //     case MALE:
        //         sex = 1;
        //         break;
        //     default:
        //         sex = 0;
        //         break;
        // }

        int highBp;
        if(stats.getSysBp() < 120 && stats.getDiaBp() <=80){
            highBp = 0;
        } else {
            highBp = 1;
        }

        System.out.println( "sys/dias: " + stats.getSysBp() + "/" + stats.getDiaBp() + " High BP? " + highBp);

        int highChol;
        if (stats.getChol() <= 5.2){
            highChol = 0; 
        } else {
            highChol = 1;
        }
        System.out.println( "chol: " + stats.getChol() + " High Chol? " + highChol);


        int isActive;
        if (stats.getExerciseTime() < 150){
            isActive = 0;
        } else {
            isActive = 1;
        }

        System.out.println("Is active? " + isActive);
        

        predictRequest.setBp(highBp);
        predictRequest.setChol(highChol);
        predictRequest.setPhysicalActivity(isActive);
        // predictRequest.setBmi(bmi);
        // predictRequest.setAgeGroup(ageGroup);
        // predictRequest.setSex(sex);

        // HARDCODED INFO PLACEHOLDER
        // predictRequest.setBp(1);
        // predictRequest.setChol(1);
        // predictRequest.setPhysicalActivity(0);
        predictRequest.setBmi(24);
        predictRequest.setAgeGroup(4);
        predictRequest.setSex(0);
        


        PredictResponse prediction = predictClient.predictDiabetes(predictRequest);
        System.out.println(">>> Probability: "+ prediction);
        if(prediction.getProbability() == 0){
            return "You are in generally good health.";
        } else {
            return "You are at risk of diabetes. Please schedule a diabetes health screening";
        }


  
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
