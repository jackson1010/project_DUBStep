package com.dubs.core.server.controller;

import com.dubs.core.server.dto.Signup;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.enums.Authority;
import com.dubs.core.server.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsSvc;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Signup signupRequest){
        log.info("POST Request:{}",signupRequest);

        userDetailsSvc.save(new Credentials(10000, signupRequest.getUsername(), signupRequest.getPassword(),
                true,true,true,true,
                Set.of(new SimpleGrantedAuthority(Authority.ADMIN.name()))));

        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody Signup signupRequest){


        return ResponseEntity.ok("Success");

    }

    //TODO: CLEANUP
    @GetMapping("/test")
    public ResponseEntity<String> testAuth(@RequestBody Signup signupRequest){
        UserDetails cred1 = userDetailsSvc.loadUserByUsername("init");
        Optional<Credentials> cred2 = userDetailsSvc.findByUserId(10000);
        log.info("GET:{} {}",cred1,cred2.get());
        return ResponseEntity.ok("Success");
    }


}
