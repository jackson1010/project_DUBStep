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
    private String model;
    private int n;
    private double temperature;
    private int max_tokens;
    private List<Message> messages;
}
