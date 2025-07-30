// src/main/java/com/musketeers/jewelverse/repository/JewelryRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.enums.JewelryStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Jewelry entity.
 * Handles all standard CRUD operations and includes custom queries for filtering and searching.
 */
@Repository
public interface JewelryRepository extends JpaRepository<Jewelry, Long> {

    // --- Basic finders ---
    List<Jewelry> findByStatus(JewelryStatus status);
    Page<Jewelry> findByStatus(JewelryStatus status, Pageable pageable);

    List<Jewelry> findByCategoryId(Long categoryId);
    Page<Jewelry> findByCategoryId(Long categoryId, Pageable pageable);

    List<Jewelry> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice);
    Page<Jewelry> findByPriceBetween(BigDecimal minPrice, BigDecimal maxPrice, Pageable pageable);

    List<Jewelry> findByNameContainingIgnoreCase(String name);
    Page<Jewelry> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Jewelry> findBySku(String sku);

    // --- Custom JPQL Queries ---
    @Query("SELECT j FROM Jewelry j WHERE j.material = :material AND j.status = :status")
    List<Jewelry> findByMaterialAndStatus(@Param("material") String material, @Param("status") JewelryStatus status);

    @Query("SELECT j FROM Jewelry j JOIN j.category c WHERE c.name = :categoryName AND j.status = :status")
    List<Jewelry> findByCategoryNameAndStatus(@Param("categoryName") String categoryName, @Param("status") JewelryStatus status);

    @Query("SELECT j FROM Jewelry j WHERE j.status = :status AND j.price BETWEEN :minPrice AND :maxPrice AND j.category.id = :categoryId")
    Page<Jewelry> findByStatusAndPriceRangeAndCategory(
            @Param("status") JewelryStatus status,
            @Param("minPrice") BigDecimal minPrice,
            @Param("maxPrice") BigDecimal maxPrice,
            @Param("categoryId") Long categoryId,
            Pageable pageable
    );

    @Query("SELECT j FROM Jewelry j JOIN j.inventory i WHERE i.quantity > 0 AND j.status = 'ACTIVE'")
    List<Jewelry> findAvailableJewelries();

    // Note: This query assumes your Inventory entity has a 'minimumStockLevel' field.
    @Query("SELECT j FROM Jewelry j JOIN j.inventory i WHERE i.quantity <= i.minimumStockLevel")
    List<Jewelry> findLowStockJewelries();

    @Query("SELECT COUNT(j) FROM Jewelry j WHERE j.status = :status")
    long countByStatus(@Param("status") JewelryStatus status);
}