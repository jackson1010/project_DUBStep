package com.dubs.core.server.dto;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatGPTRequest {

    @Value("${openai.model}")
    private String model;
    private int n;
    private List<Message> messages;
    @Value("${openai.max-completions}")
    private int maxCompletions;
    @Value("${openai.temperature}")
    private double temperature;
    @Value("${openai.max_tokens}")
    private int maxTokens;

}
