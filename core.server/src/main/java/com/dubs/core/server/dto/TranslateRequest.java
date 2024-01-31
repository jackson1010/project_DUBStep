package com.dubs.core.server.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class TranslateRequest {
    @JsonProperty("text")
    private String q;
    @JsonProperty(required = false)
    private String source;
    private String target;
    @JsonProperty(required = false)
    private String key;
    
}
