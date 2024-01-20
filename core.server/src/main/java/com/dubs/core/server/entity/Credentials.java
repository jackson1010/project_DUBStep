package com.dubs.core.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name="user_credentials",
uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Credentials implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Max(65535)
    @Min(0)
    private Integer userId;

    @NotBlank
    @Size(max = 25)
    private String username;

    @NotBlank
    @Size(max = 120)
    private String password;

    private boolean AccountNonExpired;

    private boolean AccountNonLocked;

    private boolean CredentialsNonExpired;

    private boolean Enabled;

    //TODO: FIX AUTHORITIES

    @ManyToMany
    @JoinTable(name = "user_authorities",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    Collection<BasicAuthority> authorities;

    @Override
    public Collection<BasicAuthority> getAuthorities() {
        return null;
    }

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "user_authorities",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "authority_id"))
//    private Set<Authority> authoritys;


}
