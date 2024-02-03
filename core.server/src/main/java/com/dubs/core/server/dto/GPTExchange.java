package com.dubs.core.server.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GPTExchange {

    private LocalDateTime dateTime;
    private String request;
    private String response;
}
