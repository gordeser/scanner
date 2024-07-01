package org.gordeser.scanner.facade;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.dto.ReviewDTO;
import org.gordeser.scanner.dao.entity.Review;
import org.gordeser.scanner.service.ReviewService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewFacade {
    private final ReviewService reviewService;

    public Review createReview(ReviewDTO reviewDTO, User createdBy) {
        Review newReview = new Review();

        newReview.setRating(reviewDTO.getRating());
        newReview.setTitle(reviewDTO.getTitle());
        newReview.setDescription(reviewDTO.getDescription());
        newReview.setCreatedAt(LocalDateTime.now());
        newReview.setUpdatedAt(LocalDateTime.now());
        newReview.setCreatedBy(createdBy);

        return reviewService.save(newReview);
    }

    public Review updateReviewById(Long id, ReviewDTO reviewDTO) throws Exception {
        Review updatedReview = reviewService.findById(id);
        if (updatedReview == null) {
            throw new Exception("Review not found");
        }

        updatedReview.setRating(reviewDTO.getRating());
        updatedReview.setTitle(reviewDTO.getTitle());
        updatedReview.setDescription(reviewDTO.getDescription());
        updatedReview.setUpdatedAt(LocalDateTime.now());

        return reviewService.update(updatedReview);
    }
}
