package com.dubs.core.server.dto;

import com.dubs.core.server.enums.Authority;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponse {

    private Integer userId;
    private String token;
    private String authority;

}
