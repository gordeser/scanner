package org.gordeser.scanner.dao.repository;

import org.gordeser.scanner.dao.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> getReviewsByCreatedByUsername(String createdByUsername);
}
