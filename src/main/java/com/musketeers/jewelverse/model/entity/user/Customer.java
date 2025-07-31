// src/main/java/com/musketeers/jewelverse/model/entity/user/Customer.java
// NOTE: This is your rearranged Customer entity.
// The @Data annotation has been replaced with more specific annotations.

package com.musketeers.jewelverse.model.entity.user;

import com.musketeers.jewelverse.model.entity.feedback.Feedback;
import com.musketeers.jewelverse.model.entity.order.Cart;
import com.musketeers.jewelverse.model.entity.order.Order;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a Customer in the system.
 * Extends the base User class and is identified by the 'CUSTOMER' discriminator value.
 */
@Entity
@DiscriminatorValue("CUSTOMER")
@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Customer extends User {

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "shipping_address", columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "billing_address", columnDefinition = "TEXT")
    private String billingAddress;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Feedback> feedbacks;

    // Constructor for creating a new customer
    public Customer(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName);
    }
}