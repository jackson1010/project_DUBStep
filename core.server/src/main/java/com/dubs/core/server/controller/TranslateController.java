package com.dubs.core.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dubs.core.server.dto.TranslateRequest;
import com.dubs.core.server.dto.TranslateResponse;
import com.dubs.core.server.service.TranslateService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class TranslateController {
    
     @Autowired
    private TranslateService translateSvc;

    @PostMapping(path="/api/translate")
    public ResponseEntity<String> getTranslation (@RequestBody String payload) throws JsonMappingException, JsonProcessingException{

        //payload should contain JSON containing text value and target
        
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        TranslateRequest request;
        request = objectMapper.readValue(payload, TranslateRequest.class);
        
        TranslateResponse response = translateSvc.getTranslation(request);

        return ResponseEntity.ok().body(response.getData().getTranslations().get(0).toString());
    }
    
}
