// src/main/java/com/musketeers/jewelverse/repository/RoleRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for Role entity.
 * Handles database operations for Roles.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Finds a role by its name (e.g., "CUSTOMER", "MANAGER").
     *
     * @param name The name of the role.
     * @return An Optional containing the Role if found, otherwise empty.
     */
    Optional<Role> findByName(String name);
}