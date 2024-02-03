package com.dubs.core.server.service;

import com.dubs.core.server.client.TranslateClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dubs.core.server.dto.TranslateRequest;
import com.dubs.core.server.dto.TranslateResponse;

@Service
@Slf4j
public class TranslateService {

     @Value ("${TRANSLATOR_API_KEY}")
    private String API_KEY;

    @Autowired
    private TranslateClient translateClient;

    public TranslateResponse getTranslation(TranslateRequest request){

        request.setKey(API_KEY);
        TranslateResponse response = translateClient.getTranslation(request);
        log.info("checkpoint >> {}",response.getData().getTranslations().get(0));

        return response;
    }
    
    
}
