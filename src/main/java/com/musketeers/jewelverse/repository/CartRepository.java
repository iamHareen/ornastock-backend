// src/main/java/com/musketeers/jewelverse/repository/CartRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.order.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findByCustomerId(Long customerId);

    @Query("SELECT c FROM Cart c JOIN FETCH c.cartItems WHERE c.customer.id = :customerId")
    Optional<Cart> findByCustomerIdWithItems(@Param("customerId") Long customerId);

    @Query("SELECT COUNT(ci) FROM Cart c JOIN c.cartItems ci WHERE c.customer.id = :customerId")
    int countItemsByCustomerId(@Param("customerId") Long customerId);

    boolean existsByCustomerId(Long customerId);
}