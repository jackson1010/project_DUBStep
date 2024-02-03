package com.dubs.core.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dubs.core.server.dto.PredictRequest;
import com.dubs.core.server.dto.PredictResponse;

@FeignClient(name="predictionClient", url="http://127.0.0.1:5000")
public interface PredictionClient {
    
    @PostMapping("/predict")
    PredictResponse predictDiabetes(@RequestBody PredictRequest req);

}
