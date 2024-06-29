package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.dto.CityListResDto;
import com.example.skhuthon_0th_team10.dto.CityResDto;
import com.example.skhuthon_0th_team10.service.CityService;
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
public class CityController {

    private final CityService cityService;

    @Operation(summary = "나라 도시 리스트 확인", description = "나라 도시 리스트를 확인합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/citys/{countryId}")
    public ResponseEntity<List<CityListResDto>> getCityList(@PathVariable Long countryId) {
        return new ResponseEntity<>(cityService.getCityList(countryId), HttpStatus.OK);
    }

    @Operation(summary = "도시 정보 조회", description = "도시 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("city/{cityId}")
    public ResponseEntity<CityResDto> getCityInfo(@PathVariable Long cityId) {
        return new ResponseEntity<>(cityService.getCityInfo(cityId),HttpStatus.OK);
    }
}
