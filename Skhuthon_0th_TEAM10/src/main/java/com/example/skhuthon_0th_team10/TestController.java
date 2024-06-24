package com.example.skhuthon_0th_team10;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping( "/call")
    public ResponseEntity<String> Call() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}

