package com.dubs.core.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name="user_contacts")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ContactDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(65535)
    @Min(0)
    private Integer id;

    @Max(65535)
    @Min(0)
    private Integer userId;

    private String address;

    private String email;

    private String mobileNumber;

    private String homeNumber;
}
