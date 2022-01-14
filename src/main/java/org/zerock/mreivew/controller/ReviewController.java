package org.zerock.mreivew.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zerock.mreivew.dto.ReviewDTO;
import org.zerock.mreivew.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/reviews")
@Slf4j
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/{mno}/all")
    public ResponseEntity<List<ReviewDTO>> getList(@PathVariable("mno") Long mno) {
        log.info("--------------list----------------");
        log.info("mno: {}", mno);

        List<ReviewDTO> reviewDTOList = this.reviewService.getListOfMovie(mno);

        return new ResponseEntity<>(reviewDTOList, HttpStatus.OK);
    }

    @PostMapping("/{mno}")
    public ResponseEntity<Long> addReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("--------------add MovieReview----------------");
        log.info("reviewDTO: {}", reviewDTO);

        Long reviewnum = this.reviewService.register(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @PutMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> modifyReview(@PathVariable Long reviewnum, @RequestBody ReviewDTO reviewDTO) {
        log.info("--------------modify MovieReview----------------");
        log.info("reviewDTO: {}", reviewDTO);

        this.reviewService.modify(reviewDTO);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }

    @DeleteMapping("/{mno}/{reviewnum}")
    public ResponseEntity<Long> deleteReview(@PathVariable Long reviewnum) {
        log.info("--------------delete MovieReview----------------");
        log.info("reviewnum: {}", reviewnum);

        this.reviewService.remove(reviewnum);

        return new ResponseEntity<>(reviewnum, HttpStatus.OK);
    }
}
