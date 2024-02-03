package com.dubs.core.server.controller;

import java.util.Optional;

import com.dubs.core.server.service.ChatGPTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.json.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.dubs.core.server.dto.HealthStats;
import com.dubs.core.server.dto.PredictRequest;
import com.dubs.core.server.entity.UserProfile;
import com.dubs.core.server.service.PredictService;
import com.dubs.core.server.service.UserServiceImpl;

import java.util.List;

@RestController
@RequestMapping("api")
@CrossOrigin(origins="*")
public class PredictionController {

    @Autowired
    private UserServiceImpl userDetailsSvc;

    @Autowired
    private PredictService predictSvc;

    @Autowired
    private ChatGPTService gptService;

    @GetMapping("/predict/{userId}")
    public ResponseEntity<String> predictDiabetes(@PathVariable Integer userId, HealthStats stats) throws JsonProcessingException {
         Optional<UserProfile> profile = userDetailsSvc.findProfileByUserId(userId);
         if(profile.isEmpty()){
             JsonObject response = Json.createObjectBuilder().add("Get Profile Error", "User Profile does not Exist").build();
             return ResponseEntity.badRequest().body(response.toString());
         }

         PredictRequest predictRequest = predictSvc.generatePredictRequest(profile.get(), stats);
         String healthPrediction = predictSvc.getPrediction(predictRequest);
         List<String> healthAdvisory = gptService.getAdvice(userId,predictSvc.generateIndicators(predictRequest,stats));

        JsonArrayBuilder healthAdvisoryArr = Json.createArrayBuilder();
        for (String prediction: healthAdvisory){
            healthAdvisoryArr.add(prediction);
        }

        JsonObject response = Json.createObjectBuilder()
                                .add("healthPrediction", healthPrediction)
                                .add("healthAdvisory",healthAdvisoryArr.build())
                                .build();
        return ResponseEntity.ok().body(response.toString());
    }

}
