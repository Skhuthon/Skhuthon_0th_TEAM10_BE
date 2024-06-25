package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.Token;
import com.example.skhuthon_0th_team10.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/oauth2")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("callback/google")
    public ResponseEntity<Token> googleCallback(@RequestParam(name = "code") String code) {
        String googleAccessToken = authService.getGoogleAccessToken(code);
        return new ResponseEntity<>(loginOrSignup(googleAccessToken), HttpStatus.OK);
    }

    public Token loginOrSignup(String googleAccessToken) {
        return authService.loginOrSignUp(googleAccessToken);
    }
}
