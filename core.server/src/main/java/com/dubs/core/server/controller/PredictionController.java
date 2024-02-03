package com.dubs.core.server.controller;

import java.util.Optional;

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

@RestController
@RequestMapping("api")
public class PredictionController {

    @Autowired
    private UserServiceImpl userDetailsSvc;

    @Autowired
    private PredictService predictSvc;
    

    @GetMapping("/predict/{userId}")
    public ResponseEntity<String> predictDiabetes(@PathVariable Integer userId, @RequestBody HealthStats stats){

        // Optional<UserProfile> profile = userDetailsSvc.findProfileByUserId(userId);
        // if(profile.isEmpty()){
        //     JsonObject response = Json.createObjectBuilder().add("Get Profile Error", "User Profile does not Exist").build();
        //     return ResponseEntity.badRequest().body(response.toString());
        // }

        // String healthAdvisory = predictSvc.getPrediction(profile.get(), stats);
        String healthAdvisory = predictSvc.getPrediction(null, stats);
        JsonObject response = Json.createObjectBuilder().add("healthAdvisory", healthAdvisory).build();
        return ResponseEntity.ok().body(response.toString());
    }

}
