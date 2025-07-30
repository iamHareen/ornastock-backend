// src/main/java/com/musketeers/jewelverse/repository/InventoryRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.jewelry.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Inventory entity.
 * Handles database operations for stock levels.
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    /**
     * Finds the inventory record for a specific jewelry item.
     * @param jewelryId The ID of the jewelry.
     * @return An Optional containing the Inventory if found.
     */
    Optional<Inventory> findByJewelryId(Long jewelryId);

    /**
     * Finds all inventory records where the quantity is below a certain threshold.
     * @param quantity The stock quantity threshold.
     * @return A list of low-stock inventory records.
     */
    List<Inventory> findByQuantityLessThanEqual(int quantity);

    /**
     * Finds all inventory records where the quantity is zero or less.
     * @return A list of out-of-stock inventory records.
     */
    @Query("SELECT i FROM Inventory i WHERE i.quantity <= 0")
    List<Inventory> findOutOfStockItems();
}