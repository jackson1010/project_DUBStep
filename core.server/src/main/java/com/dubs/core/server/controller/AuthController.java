package com.dubs.core.server.controller;

import com.dubs.core.server.dto.AuthRequest;
import com.dubs.core.server.dto.AuthResponse;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.enums.Authority;
import com.dubs.core.server.security.JWTService;
import com.dubs.core.server.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserDetailsServiceImpl userDetailsSvc;

    @Autowired
    JWTService jwtService;

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody AuthRequest signupRequest){
        log.info("POST Request:{}",signupRequest);

        //TODO: HANDLE THIS BETTER
        if(userDetailsSvc.existsByUsername(signupRequest.getUsername()))
            return ResponseEntity.badRequest().body("You already exist");

        //TODO: PLEASE RETHINK THIS.
        userDetailsSvc.save(new Credentials(10000, signupRequest.getUsername(), passwordEncoder.encode(signupRequest.getPassword()),
                true,true,true,true,
                signupRequest.getUsername().contains("ADMIN") ? Authority.ADMIN : Authority.USER));

        return ResponseEntity.ok("Signup Success");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> loginUser(@RequestBody AuthRequest signinRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signinRequest.getUsername(), signinRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        AuthResponse response = new AuthResponse(jwtService.generateToken(signinRequest.getUsername()),
                authentication.getAuthorities().stream().toList().get(0).toString());

        return ResponseEntity.ok(response.toString());

    }

    //TODO: CLEANUP
    @GetMapping("/test")
    public ResponseEntity<String> testAuth(){
        Credentials cred1 = (Credentials) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("GET:{}",cred1);
        return ResponseEntity.ok("Auth Test Success");
    }


}
