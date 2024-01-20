package com.dubs.core.server.controller;

import com.dubs.core.server.constants.Constants;
import com.dubs.core.server.dto.ChatGPTRequest;
import com.dubs.core.server.dto.ChatGPTResponse;
import com.dubs.core.server.dto.Message;
import com.dubs.core.server.services.GptClient;
import feign.Feign;
import feign.FeignException;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
public class GptController {
    private final GptClient gptClient;

    private final String gptKey;

    public GptController(GptClient gptClient, @Value("${gpt.api.key}") String gptKey) {
        this.gptClient = gptClient;
        this.gptKey = gptKey;
    }

    @PostMapping("/gpt")
    public ResponseEntity<String> getGPTResponse(@RequestBody String prompt) {
        try {
            ChatGPTRequest request = new ChatGPTRequest();
            request.setMessages(List.of(new Message("user", prompt)));
            ChatGPTResponse response = gptClient.sendPrompt("Bearer " + gptKey, request);
            return ResponseEntity.ok(response.getContent());
        } catch (FeignException e) {
            log.error("FeignException occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling GPT API");
        } catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

}
