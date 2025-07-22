package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.entity.order.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(Long cartId);

    Optional<CartItem> findByCartIdAndJewelryId(Long cartId, Long jewelryId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.customer.id = :customerId")
    List<CartItem> findByCustomerId(@Param("customerId") Long customerId);

    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.customer.id = :customerId AND ci.jewelry.id = :jewelryId")
    Optional<CartItem> findByCustomerIdAndJewelryId(@Param("customerId") Long customerId, @Param("jewelryId") Long jewelryId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.id = :cartId")
    void deleteByCartId(@Param("cartId") Long cartId);

    @Modifying
    @Query("DELETE FROM CartItem ci WHERE ci.cart.customer.id = :customerId")
    void deleteByCustomerId(@Param("customerId") Long customerId);
}
