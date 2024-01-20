package com.dubs.core.server.controller;

import com.dubs.core.server.dto.ChatGPTRequest;
import com.dubs.core.server.dto.ChatGPTResponse;
import com.dubs.core.server.dto.Message;
import com.dubs.core.server.services.GptClient;
import feign.FeignException;
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
    @Value("${openai.api.key}")
    private String gptKey;
    @Value("${openai.model}")
    private String model;
    @Value("${openai.temperature}")
    private double temperature;
    @Value("${openai.max_tokens}")
    private int max_tokens;
    private final GptClient gptClient;

    public GptController(GptClient gptClient) {
        this.gptClient = gptClient;
    }

    @PostMapping("/gpt")
    public ResponseEntity<String> getGPTResponse(@RequestBody String msg) {
        try {
            ChatGPTRequest request = new ChatGPTRequest(model, 1, temperature, max_tokens, List.of(new Message("user", msg)));
            log.info(String.valueOf(request));
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
