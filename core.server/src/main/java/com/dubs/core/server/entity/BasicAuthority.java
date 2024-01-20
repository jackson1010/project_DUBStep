//package com.dubs.core.server.entity;
//
//import jakarta.persistence.*;
//import jakarta.validation.constraints.Max;
//import jakarta.validation.constraints.Min;
//import lombok.*;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.security.PrivateKey;
//import java.util.Set;
//
//@Entity
//@Table(name = "user_authorities")
//@Getter
//@Setter
//@ToString
//@NoArgsConstructor
//@AllArgsConstructor
//public class BasicAuthority implements GrantedAuthority {
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name="user_id", nullable=false)
//    private Credentials user;
//
//    @Id
//    private com.dubs.core.server.enums.Authority authority;
//
//    @Override
//    public String getAuthority() {
//        return authority.name();
//    }
//
//}
