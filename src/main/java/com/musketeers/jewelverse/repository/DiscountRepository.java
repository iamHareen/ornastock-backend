// src/main/java/com/musketeers/jewelverse/repository/DiscountRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.discount.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repository interface for Discount entity.
 * Handles database operations for applied discounts.
 */
@Repository
public interface DiscountRepository extends JpaRepository<Discount, Long> {

    /**
     * Finds all discounts applied to a specific jewelry item.
     * @param jewelryId The ID of the jewelry.
     * @return A list of discounts for the specified jewelry.
     */
    List<Discount> findByJewelryId(Long jewelryId);

    /**
     * Finds all discounts that are currently active.
     * Assumes the Discount entity has an 'isActive' boolean field.
     * @return A list of all active discounts.
     */
    List<Discount> findByIsActiveTrue();

    /**
     * Finds all discounts that have not yet expired.
     * Assumes the Discount entity has an 'endDate' field.
     * @param date The current date and time.
     * @return A list of non-expired discounts.
     */
    List<Discount> findByEndDateAfter(LocalDateTime date);
}