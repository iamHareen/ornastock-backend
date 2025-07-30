package com.musketeers.jewelverse.model.entity.feedback;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.user.Customer;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "feedbacks")
public class Feedback extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id")
    private Jewelry jewelry;

    @Column(name = "rating", nullable = false)
    private Integer rating; // 1-5 stars

    @Column(name = "title")
    private String title;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @Column(name = "is_verified_purchase")
    private Boolean isVerifiedPurchase = false;

    @Column(name = "is_approved")
    private Boolean isApproved = false;

    @Column(name = "admin_response", columnDefinition = "TEXT")
    private String adminResponse;

    // Constructors
    public Feedback() {}

    public Feedback(Customer customer, Jewelry jewelry, Integer rating, String title, String comment) {
        this.customer = customer;
        this.jewelry = jewelry;
        this.rating = rating;
        this.title = title;
        this.comment = comment;
    }
}
