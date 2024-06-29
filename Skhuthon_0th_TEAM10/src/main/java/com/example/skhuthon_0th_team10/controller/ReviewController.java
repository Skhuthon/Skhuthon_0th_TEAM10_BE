package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.dto.ReviewInfoResDto;
import com.example.skhuthon_0th_team10.dto.ReviewListResDto;
import com.example.skhuthon_0th_team10.dto.ReviewSaveReqDto;
import com.example.skhuthon_0th_team10.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @Operation(summary = "로그인한 사람이 작성한 리뷰 리스트 조회", description = "로그인한 사람이 작성한 리뷰 리스트를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping()
    public ResponseEntity<ReviewListResDto> reviewFindAll(Principal principal) {
        ReviewListResDto reviewListResDto = reviewService.reviewFindAll(principal);
        return new ResponseEntity<>(reviewListResDto, HttpStatus.OK);
    }

    @Operation(summary = "해당 도시를 주제로 작성한 리뷰 조회", description = "해당 도시를 주제로 작성한 리뷰를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @GetMapping("/{cityId}")
    public ResponseEntity<List<ReviewInfoResDto>> reviewFindOne(@PathVariable Long cityId) {
        return new ResponseEntity<>(reviewService.findReviewList(cityId), HttpStatus.OK);
    }

    @Operation(summary = "리뷰 작성", description = "리뷰를 작성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @ResponseBody
    @PostMapping(value = "/{cityId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> reviewSave(@RequestPart("review") ReviewSaveReqDto reviewSaveReqDto,
                                             @RequestPart("image") MultipartFile image,
                                             @PathVariable Long cityId,
                                             Principal principal) throws IOException {
        reviewService.reviewSave(reviewSaveReqDto, image, cityId, principal);
        return new ResponseEntity<>("후기 저장이 완료되었습니다.", HttpStatus.CREATED);
    }

    @Operation(summary = "리뷰 삭제", description = "리뷰를 삭제합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 생성을 성공했습니다"),
            @ApiResponse(responseCode = "400", description = "잘못된 요청입니다"),
            @ApiResponse(responseCode = "401", description = "헤더 없음 or 토큰 불일치",
                    content = @Content(schema = @Schema(example = "INVALID_HEADER or INVALID_TOKEN")))
    })
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable("reviewId") Long reviewId) {
        reviewService.deleteReview(reviewId);
        return new ResponseEntity<>("후기 삭제가 완료되었습니다.", HttpStatus.OK);
    }

}
