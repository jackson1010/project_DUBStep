package com.dubs.core.server.controller;

import com.dubs.core.server.dto.Signup;
import com.dubs.core.server.entity.BasicAuthority;
import com.dubs.core.server.entity.Credentials;
import com.dubs.core.server.enums.Authority;
import com.dubs.core.server.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
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

    @PostMapping("/post")
    public ResponseEntity<String> testPost(@RequestBody String test){
        log.info("POST Request:{}",test);
        return ResponseEntity.ok("Success");
    }

    @GetMapping("/get")
    public ResponseEntity<String> testGet(){
        return ResponseEntity.ok("Success");
    }

}
