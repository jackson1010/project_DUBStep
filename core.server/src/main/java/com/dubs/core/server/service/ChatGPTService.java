package com.dubs.core.server.service;

import com.dubs.core.server.client.GptClient;
import com.dubs.core.server.dto.*;
import com.dubs.core.server.entity.GPTChatHistory;
import com.dubs.core.server.repository.GPTRespository;
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
    private GPTRespository gptRespository;


    @Autowired
    private MongoTemplate mongoTemplate;



    public ChatGPTService(GptClient gptClient) {
        this.gptClient = gptClient;
    }

    public ChatGPTResponse chat(Integer userId,String msg,Integer number) throws FeignException {
        ChatGPTRequest request = null;
        if(number==1){
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.firstPrompt + msg)));
        }else if(number==2){
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.secondPrompt + msg)));
        }else if(number==3){
            request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", Prompt.thirdPrompt + msg)));
        }
        ChatGPTResponse response = gptClient.sendPrompt("Bearer " + gptKey, request);
        //TODO: check if will have more than one message
        logChat(userId, request.getMessages().get(0), response.getChoices().get(0));
        return response;
    }

    private void logChat(Integer userId, Message message, Choice choice) {

        //TODO: use userid conversation id?
        Query query = Query.query(Criteria.where("userId").is(userId));
        GPTExchange exchange = new GPTExchange(LocalDateTime.now(), message.getContent(), choice.getMessage().getContent());
        Update updateOps = new Update().push("history").each(exchange);

        UpdateResult updateResult = mongoTemplate.upsert(query,updateOps,"gpt");
        log.info("GPT Exchange updated: {}", updateResult);
    }

    public List<GPTChatHistory> findAllByUserId(Integer userId){
        return gptRespository.findAllByUserId(userId);
    }

}
