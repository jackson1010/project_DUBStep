package com.dubs.core.server.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dubs.core.server.dto.TranslateRequest;
import com.dubs.core.server.dto.TranslateResponse;

@FeignClient(name="translate-service",url="https://translation.googleapis.com/language/")
public interface TranslateClient {

     @PostMapping("translate/v2")
     TranslateResponse getTranslation(@RequestBody TranslateRequest request);
}
