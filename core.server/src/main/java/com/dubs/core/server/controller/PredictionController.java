package com.dubs.core.server.controller;

import java.util.Optional;

import com.dubs.core.server.service.ChatGPTService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dubs.core.server.dto.HealthStats;
import com.dubs.core.server.dto.PredictRequest;
import com.dubs.core.server.entity.UserProfile;
import com.dubs.core.server.service.PredictService;
import com.dubs.core.server.service.UserServiceImpl;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import java.util.List;

@RestController
@RequestMapping("api")
public class PredictionController {

    @Autowired
    private UserServiceImpl userDetailsSvc;

    @Autowired
    private PredictService predictSvc;

    @Autowired
    private ChatGPTService gptService;

    @Autowired
    ObjectMapper objectMapper;


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

        JsonObject response = Json.createObjectBuilder()
                                .add("healthPrediction", healthPrediction)
                                .add("healthAdvisory",objectMapper.writeValueAsString(healthAdvisory))
                                .build();
        return ResponseEntity.ok().body(response.toString());
    }

}
