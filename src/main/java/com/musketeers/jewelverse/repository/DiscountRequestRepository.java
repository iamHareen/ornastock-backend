// src/main/java/com/musketeers/jewelverse/repository/DiscountRequestRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.discount.DiscountRequest;
import com.musketeers.jewelverse.model.enums.DiscountRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for DiscountRequest entity.
 */
@Repository
public interface DiscountRequestRepository extends JpaRepository<DiscountRequest, Long> {

    /**
     * Finds all discount requests with a specific status.
     * Useful for managers to find all PENDING requests.
     *
     * @param status The status to filter by.
     * @return A list of matching discount requests.
     */
    List<DiscountRequest> findByStatus(DiscountRequestStatus status);
}
