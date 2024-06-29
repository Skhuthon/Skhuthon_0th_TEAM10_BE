package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CountryListResDto;
import com.example.skhuthon_0th_team10.dto.CountryResDto;
import com.example.skhuthon_0th_team10.service.CountryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @Operation(summary = "나라 정보 조회", description = "나라 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/country/{countryId}") // 단일 조회
    public ResponseEntity<CountryResDto> getCountryInfo(@PathVariable Long countryId) {
                return new ResponseEntity<>(countryService.getCountryInfo(countryId), HttpStatus.OK);
    }

    @Operation(summary = "나라 리스트 조회", description = "나라 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/country") // 전체 조회
    public ResponseEntity<List<CountryListResDto>> getCountryList() {
        return new ResponseEntity<>(countryService.getCountryList(), HttpStatus.OK);
    }
}
