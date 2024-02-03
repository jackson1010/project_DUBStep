package com.dubs.core.server.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class GPTRequestDTO {

    String query;
    Integer number;
}
