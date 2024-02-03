package com.dubs.core.server.dto;

import com.dubs.core.server.entity.ContactDetails;
import com.dubs.core.server.entity.UserProfile;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileDTO {

    private Integer userId;
    UserProfile userProfile;
    ContactDetails contactDetails;

}
