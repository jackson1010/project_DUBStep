package com.dubs.core.server.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dubs.core.server.dto.TranslateRequest;
import com.dubs.core.server.dto.TranslateResponse;

@Service
public class TranslateService {

     @Value ("${TRANSLATOR_API_KEY}")
    private String API_KEY;

    @Autowired
    private TranslateClient translateClient;

    public TranslateResponse getTranslation(TranslateRequest request){

        request.setKey(API_KEY);

        TranslateResponse response = translateClient.getTranslation(request);
        System.out.println("checkpoint >> " + response.getData().getTranslations().get(0));

        return response;
    }
    
    
}
