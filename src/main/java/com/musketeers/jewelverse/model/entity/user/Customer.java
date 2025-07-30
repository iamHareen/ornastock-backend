package com.musketeers.jewelverse.model.entity.user;

import com.musketeers.jewelverse.model.entity.feedback.Feedback;
import com.musketeers.jewelverse.model.entity.order.Cart;
import com.musketeers.jewelverse.model.entity.order.Order;
import com.musketeers.jewelverse.model.enums.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
@PrimaryKeyJoinColumn(name = "user_id")
public class Customer extends User {

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "billing_address")
    private String billingAddress;

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Feedback> feedbacks;

    // Constructors
    public Customer() {
        super();
        setRole(UserRole.CUSTOMER);
    }

    public Customer(String email, String password, String firstName, String lastName) {
        super(email, password, firstName, lastName, UserRole.CUSTOMER);
    }

}