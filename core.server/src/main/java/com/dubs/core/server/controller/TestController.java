package com.dubs.core.server.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/test")
@Slf4j
@CrossOrigin(origins="*")
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
