package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.dto.ReviewInfoResDto;
import com.example.skhuthon_0th_team10.dto.ReviewListResDto;
import com.example.skhuthon_0th_team10.dto.ReviewSaveReqDto;
import com.example.skhuthon_0th_team10.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping()
    public ResponseEntity<ReviewListResDto> reviewFindAll() {
        ReviewListResDto reviewListResDto = reviewService.reviewFindAll();
        return new ResponseEntity<>(reviewListResDto, HttpStatus.OK);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewInfoResDto> reviewFindOne(@PathVariable("reviewId") Long reviewId) {
        ReviewInfoResDto reviewInfoResDto = reviewService.reviewFindOne(reviewId);
        return new ResponseEntity<>(reviewInfoResDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> reviewSave(@RequestBody ReviewSaveReqDto reviewSaveReqDto) {
        reviewService.reviewSave(reviewSaveReqDto);
        return new ResponseEntity<>("후기 저장이 완료되었습니다.", HttpStatus.CREATED);
    }
}
