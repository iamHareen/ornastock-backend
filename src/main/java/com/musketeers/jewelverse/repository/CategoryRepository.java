package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.entity.jewelry.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByName(String name);

    List<Category> findByNameContainingIgnoreCase(String name);

    boolean existsByName(String name);

    @Query("SELECT c FROM Category c WHERE SIZE(c.jewelries) > 0")
    List<Category> findCategoriesWithJewelries();

    @Query("SELECT c FROM Category c WHERE SIZE(c.jewelries) = 0")
    List<Category> findEmptyCategories();

    @Query("SELECT c, COUNT(j) FROM Category c LEFT JOIN c.jewelries j GROUP BY c ORDER BY COUNT(j) DESC")
    List<Object[]> findCategoriesWithJewelryCount();

    @Query("SELECT COUNT(j) FROM Category c JOIN c.jewelries j WHERE c.id = :categoryId")
    long countJewelriesByCategoryId(@Param("categoryId") Long categoryId);
}

