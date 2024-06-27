package com.example.skhuthon_0th_team10.controller;

import com.example.skhuthon_0th_team10.domain.Review;
import com.example.skhuthon_0th_team10.dto.ReviewInfoResDto;
import com.example.skhuthon_0th_team10.dto.ReviewListResDto;
import com.example.skhuthon_0th_team10.dto.ReviewSaveReqDto;
import com.example.skhuthon_0th_team10.service.ReviewService;
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

    @GetMapping()
    public ResponseEntity<ReviewListResDto> reviewFindAll() {
        ReviewListResDto reviewListResDto = reviewService.reviewFindAll();
        return new ResponseEntity<>(reviewListResDto, HttpStatus.OK);
    }

    @GetMapping("/{cityId}")
    public ResponseEntity<List<ReviewInfoResDto>> reviewFindOne(@PathVariable Long cityId) {
        return new ResponseEntity<>(reviewService.findReviewList(cityId), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/{cityId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> reviewSave(@RequestPart("review") ReviewSaveReqDto reviewSaveReqDto,
                                             @RequestPart("image") MultipartFile image,
                                             @PathVariable Long cityId,
                                             Principal principal) throws IOException {
        reviewService.reviewSave(reviewSaveReqDto, image, cityId, principal);
        return new ResponseEntity<>("후기 저장이 완료되었습니다.", HttpStatus.CREATED);
    }
}
