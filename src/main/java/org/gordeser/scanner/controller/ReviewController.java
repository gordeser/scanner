package org.gordeser.scanner.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.ReviewDTO;
import org.gordeser.scanner.dao.entity.Review;
import org.gordeser.scanner.dao.entity.User;
import org.gordeser.scanner.facade.ReviewFacade;
import org.gordeser.scanner.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/review")
public class ReviewController {
    private final ReviewService reviewService;
    private final ReviewFacade reviewFacade;

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> allReviews = reviewService.findAll();

        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        Review review = reviewService.findById(reviewId);

        return ResponseEntity.ok(review);
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody @Valid ReviewDTO reviewDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Review review = reviewFacade.createReview(reviewDTO, user);
        return ResponseEntity.ok(review);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReviewById(@PathVariable Long reviewId, @RequestBody @Valid ReviewDTO reviewDTO) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Review review = reviewFacade.updateReviewById(reviewId, reviewDTO, user);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long reviewId) {
        reviewService.deleteById(reviewId);
        return ResponseEntity.noContent().build();
    }
}
