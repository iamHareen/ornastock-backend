// src/main/java/com/musketeers/jewelverse/repository/FeedbackRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Feedback entity.
 */
@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

    /**
     * Finds all feedback submitted by a specific customer.
     * @param customerId The ID of the customer.
     * @return A list of feedback from the customer.
     */
    List<Feedback> findByCustomerId(Long customerId);

    /**
     * Finds all feedback for a specific jewelry item.
     * @param jewelryId The ID of the jewelry item.
     * @return A list of feedback for the jewelry item.
     */
    List<Feedback> findByJewelryId(Long jewelryId);

    /**
     * Finds all feedback with a rating greater than or equal to the specified value.
     * @param rating The minimum rating (e.g., 4).
     * @return A list of feedback with high ratings.
     */
    List<Feedback> findByRatingGreaterThanEqual(int rating);
}