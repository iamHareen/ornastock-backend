package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByEmailAndEnabledTrue(String email);

    List<Customer> findByDateOfBirth(LocalDate dateOfBirth);

    @Query("SELECT c FROM Customer c WHERE c.shippingAddress LIKE %:address%")
    List<Customer> findByShippingAddressContaining(@Param("address") String address);

    @Query("SELECT c FROM Customer c JOIN c.orders o WHERE o.id = :orderId")
    Optional<Customer> findByOrderId(@Param("orderId") Long orderId);

    @Query("SELECT COUNT(c) FROM Customer c WHERE c.enabled = true")
    long countActiveCustomers();
}
