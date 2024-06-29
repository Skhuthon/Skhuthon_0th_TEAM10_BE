package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CloudResponseDto;
import com.example.skhuthon_0th_team10.service.CloudWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CloudController {
    private final CloudWordService cloudWordService;

    @Operation(summary = "워드 클라우드 조회", description = "워드 클라우드 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/cloud")
    public ResponseEntity<CloudResponseDto> getCloud() {
        return new ResponseEntity<>(cloudWordService.getWordCloud(), HttpStatus.OK);
    }
}
