package com.dubs.core.server.controller;

import com.dubs.core.server.dto.Signup;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.enums.Authority;
import com.dubs.core.server.repository.CredentialRepository;
import com.dubs.core.server.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("api/test")
@Slf4j
public class TestController {
    //TODO: CLEANUP
    @Autowired
    UserDetailsServiceImpl userDetailsSvc;
    @PostMapping("/post")
    public ResponseEntity<String> testPost(@RequestBody String test){
        log.info("POST Request:{}",test);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/get")
    public ResponseEntity<String> testGet(){
        UserDetails cred1 = userDetailsSvc.loadUserByUsername("init");
        Optional<Credentials> cred2 = userDetailsSvc.findByUserId(10000);
        log.info("GET:{} {}",cred1,cred2.get());
        return ResponseEntity.ok("Success");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody Signup signupRequest){
        log.info("POST Request:{}",signupRequest);
        userDetailsSvc.save(new Credentials(null, signupRequest.getUsername(), signupRequest.getPassword(),
                true,true,true,true,null,
                Set.of(new com.dubs.core.server.entity.Authority(null,Authority.ADMIN))));

        return ResponseEntity.ok("Success");
    }


}
