package com.dubs.core.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;

import com.dubs.core.server.dto.TranslateRequest;
import com.dubs.core.server.dto.TranslateResponse;

@FeignClient(name="translate-service",url="https://translation.googleapis.com/language/translate/v2")
public interface TranslateClient {
     TranslateResponse getTranslation(@SpringQueryMap TranslateRequest request);
}
