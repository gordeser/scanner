package org.gordeser.scanner.service;

import lombok.RequiredArgsConstructor;
import org.gordeser.scanner.dao.entity.Review;
import org.gordeser.scanner.dao.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public List<Review> findAll() {
        return reviewRepository.findAll();
    }

    public Review findById(Long id) {
        return reviewRepository.findById(id).orElse(null);
    }

    public Review save(Review review) {
        return reviewRepository.save(review);
    }

    public Review update(Review review) {
        return reviewRepository.save(review);
    }

    public void deleteById(Long id) {
        Review review = findById(id);
        if (review != null) {
            reviewRepository.delete(review);
        }
    }

    public List<Review> getReviewsByUsername(String username) {
        return reviewRepository.getReviewsByCreatedByUsername(username);
    }
}
