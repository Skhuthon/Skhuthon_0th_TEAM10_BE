package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.UserInfo;
import com.example.skhuthon_0th_team10.dto.UserResDto;
import com.example.skhuthon_0th_team10.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public ResponseEntity<UserResDto> getUserInfo(Principal principal) {
        return new ResponseEntity<>(userService.getUserInfo(principal), HttpStatus.OK);
    }
}
