package com.dubs.core.server.dto;

import com.dubs.core.server.enums.Authority;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CredentialsDTO {

    private Integer userId;
    private String username;
    private String password;

    private boolean AccountNonExpired;
    private boolean AccountNonLocked;
    private boolean CredentialsNonExpired;
    private boolean Enabled;

    private Authority authority;
}
