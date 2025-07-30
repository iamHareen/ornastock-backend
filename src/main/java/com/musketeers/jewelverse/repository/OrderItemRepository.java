// src/main/java/com/musketeers/jewelverse/repository/OrderItemRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.order.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for OrderItem entity.
 * Handles database operations for individual items within an order.
 */
@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    /**
     * Finds all items associated with a specific order.
     * @param orderId The ID of the order.
     * @return A list of order items for the given order.
     */
    List<OrderItem> findByOrderId(Long orderId);

    /**
     * Finds the top-selling jewelry items based on the quantity sold across all orders.
     * This is useful for business intelligence and reporting.
     * @param pageable Limits the number of results (e.g., top 10).
     * @return A list of object arrays, where each array contains the jewelry ID and the total quantity sold.
     */
    @Query("SELECT oi.jewelry.id, SUM(oi.quantity) as totalQuantity FROM OrderItem oi GROUP BY oi.jewelry.id ORDER BY totalQuantity DESC")
    List<Object[]> findTopSellingJewelry(Pageable pageable);
}