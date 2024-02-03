package com.dubs.core.server.controller;

import com.dubs.core.server.dto.ChatGPTResponse;
import com.dubs.core.server.dto.GPTRequestDTO;
import com.dubs.core.server.dto.HealthIndicators;
import com.dubs.core.server.entity.GPTChatHistory;
import com.dubs.core.server.service.ChatGPTService;
import com.dubs.core.server.service.IndicatorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api")
@CrossOrigin(origins="*")
public class GptController {

    @Autowired
    ChatGPTService gptService;

    @Autowired
    IndicatorService indicatorService;

    @Autowired
    ObjectMapper objectMapper;

    @PostMapping("/gpt/{userId}")
    public ResponseEntity<String> getGPTResponse(@PathVariable Integer userId, @RequestBody GPTRequestDTO requestDTO) {
        try {
            ChatGPTResponse response = gptService.chat(userId,requestDTO.getQuery(), requestDTO.getNumber());
            return ResponseEntity.ok(response.getContent().replace("-", ""));
        } catch(FeignException e) {
            log.error("FeignException occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling GPT API");
        }
        catch (Exception e) {
            log.error("Exception occurred: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }

    @GetMapping("/gpt/getAll/{userid}")
    public ResponseEntity<String> getAllExchangesByUserId(@PathVariable Integer userid) throws JsonProcessingException {

        List<GPTChatHistory> reports = gptService.findAllByUserId(userid);

        return ResponseEntity.ok().body(objectMapper.writeValueAsString(reports));
    }

    @PostMapping("/gpt/advice/{userId}")
    public ResponseEntity<?> getGPTAdviceResponse(@PathVariable Integer userId, @RequestBody GPTRequestDTO requestDTO) {
        HealthIndicators indicators = indicatorService.getIndicators(requestDTO.getQuery());
        log.info(indicators.toString());
        List<String> responses = new ArrayList<>();

        for(String indicator: indicators.getIndicator()){
                String[] parts = indicator.split(":");
                String indi = parts[0].trim();
            try {
                ChatGPTResponse response = gptService.chat(userId, indicator, requestDTO.getNumber());
                StringBuilder resultBuilder = new StringBuilder();
                resultBuilder.append(indi).append("\n").append(response.getContent().replace("-", ""));
                String result = resultBuilder.toString();
                responses.add(result);
            } catch (FeignException e) {
                log.error("FeignException occurred: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error calling GPT API");
            } catch (Exception e) {
                log.error("Exception occurred: {}", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
            }

        }
        return ResponseEntity.ok(responses);
    }
}
