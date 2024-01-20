package com.dubs.core.server.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
//TODO: remove field injection
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    JWTAuthFilter jwtAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoderBcrypt() {
        //default Rounds is 10
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProviderDAO() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService((UserDetailsService) userDetailsServiceImpl);
        authProvider.setPasswordEncoder(passwordEncoderBcrypt());

        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                //TODO: CHANGE TO VALUE IMPORT
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/api/auth/signup").permitAll()
                    .requestMatchers("/api/auth/signin").permitAll()
                    .requestMatchers("/api/auth/**").authenticated()
                    .anyRequest().permitAll()
            )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //session persistence not required due to token
            .authenticationProvider(authenticationProviderDAO())
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
            // TODO:
            // .exceptionHandling(exception -> exception.authenticationEntryPoint(authExceptionHandler))
        return http.build();
    }
}
