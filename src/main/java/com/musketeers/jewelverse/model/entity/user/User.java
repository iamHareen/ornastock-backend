// src/main/java/com/musketeers/jewelverse/model/entity/user/User.java
// NOTE: This is the abstract base class for all user types, optimized with Lombok annotations.

package com.musketeers.jewelverse.model.entity.user;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

/**
 * Abstract base class for all users in the system.
 * Uses a SINGLE_TABLE inheritance strategy with a discriminator column 'user_type'.
 */
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
// --- Lombok Annotations for reducing boilerplate code ---
@Getter
@Setter
@EqualsAndHashCode(callSuper = true) // Includes parent class fields in equals/hashCode calculation
@ToString(callSuper = true) // Includes parent class fields in toString output
@NoArgsConstructor // Generates a no-argument constructor: public User() {}
public abstract class User extends BaseEntity {

    @Column(nullable = false)
    @NonNull // Lombok annotation that adds null checks in constructors/setters
    private String firstName;

    @Column(nullable = false)
    @NonNull // Lombok annotation that adds null checks in constructors/setters
    private String lastName;

    @Column(nullable = false, unique = true)
    @NonNull // Lombok annotation that adds null checks in constructors/setters
    private String email;

    @Column(nullable = false)
    @NonNull // Lombok annotation that adds null checks in constructors/setters
    private String password;

    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
    private Role role;

    // Custom constructor for backwards compatibility (if needed)
    // This replaces your original 4-parameter constructor
    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.enabled = true; // Set default value explicitly
    }
}