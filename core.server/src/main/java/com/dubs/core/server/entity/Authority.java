package com.dubs.core.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = "authority")}
)
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(65535)
    @Min(0)
    private Integer authority_Id;


    @Enumerated(EnumType.STRING)
    @Column(length = 30)
    @Max(30)
    private com.dubs.core.server.enums.Authority authority;
}
