// src/main/java/com/musketeers/jewelverse/repository/UserRepository.java

package com.musketeers.jewelverse.repository;

import com.musketeers.jewelverse.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for User entity.
 * Handles database operations for Users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Finds a user by their email address.
     * The Optional container handles cases where a user might not be found.
     *
     * @param email The email to search for.
     * @return An Optional containing the User if found, otherwise empty.
     */
    Optional<User> findByEmail(String email);

    /**
     * Checks if a user exists with the given email address.
     * This is more efficient than fetching the whole user object.
     *
     * @param email The email to check.
     * @return true if a user with the email exists, false otherwise.
     */
    Boolean existsByEmail(String email);
}