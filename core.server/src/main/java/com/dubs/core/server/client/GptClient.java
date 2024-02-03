package com.dubs.core.server.client;

import com.dubs.core.server.dto.ChatGPTRequest;
import com.dubs.core.server.dto.ChatGPTResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "gptClient", url = "https://api.openai.com/v1/chat")
public interface GptClient {
    @PostMapping("/completions")
    ChatGPTResponse sendPrompt(@RequestHeader("Authorization") String authorization, @RequestBody ChatGPTRequest request);
}
