package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CloudResponseDto;
import com.example.skhuthon_0th_team10.service.CloudWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CloudController {
    private final CloudWordService cloudWordService;

    @GetMapping("/cloud")
    public ResponseEntity<CloudResponseDto> getCloud() {
        return new ResponseEntity<>(cloudWordService.getWordCloud(), HttpStatus.OK);
    }
}
