package com.dubs.core.server.dto;

import lombok.Data;

@Data
public class Translation {

    private String translatedText;
    private String detectedSourceLanguage;
    
}
