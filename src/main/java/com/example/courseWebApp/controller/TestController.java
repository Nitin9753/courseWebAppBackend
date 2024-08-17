package com.example.courseWebApp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @GetMapping
    public ResponseEntity<?> test(){
        return new ResponseEntity<>("<h1>Congrats! The server is live</h1>", HttpStatus.OK);
    }
}
