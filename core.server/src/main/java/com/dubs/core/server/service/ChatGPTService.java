package com.dubs.core.server.service;

import com.dubs.core.server.services.GptClient;
import com.dubs.core.server.dto.*;
import com.mongodb.client.result.UpdateResult;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Slf4j
public class ChatGPTService {

    @Value("${openai.api.key}")
    private String gptKey;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.temperature}")
    private double temperature;
    @Value("${openai.max_tokens}")
    private int max_tokens;

    private final GptClient gptClient;
    


    @Autowired
    private MongoTemplate mongoTemplate;



    public ChatGPTService(GptClient gptClient) {
        this.gptClient = gptClient;
    }

    public ChatGPTResponse chat(Integer userId,String msg,Integer number) throws FeignException {
        ChatGPTRequest request = null;
        ChatGPTResponse response = null;
        if (number == 1) {
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.firstPrompt + msg)));
        } else if (number == 2) {
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.secondPrompt + msg)));
        } else if (number == 3) {
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.thirdPrompt + msg)));
        } else {
            response = gptClient.sendPrompt("Bearer " + gptKey, request);
        }
        //TODO: check if will have more than one message
        logChat(userId, request.getMessages().get(0), response.getChoices().get(0));
        return response;
    }

    private void logChat(Integer userId, Message message, Choice choice) {

        //TODO: use userid conversation id?
        Query query = Query.query(Criteria.where("userId").is(userId));


        log.info("GPT Exchange updated: {}");
    }
    

}
