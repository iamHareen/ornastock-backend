// src/main/java/com/musketeers/jewelverse/model/entity/discount/DiscountRequest.java
// NOTE: This entity has been updated to combine the best features of both versions.

package com.musketeers.jewelverse.model.entity.discount;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.user.Customer;
import com.musketeers.jewelverse.model.entity.user.User;
import com.musketeers.jewelverse.model.enums.DiscountRequestStatus;
import com.musketeers.jewelverse.model.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "discount_requests")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class DiscountRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requested_by_user_id", nullable = false) // The Sales Assistant who made the request
    private User requestedByUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id") // The customer for whom the discount is requested
    private Customer customer;

    @Enumerated(EnumType.STRING)
    @Column(name = "requested_discount_type", nullable = false)
    private DiscountType requestedDiscountType;

    @Column(name = "requested_discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal requestedDiscountValue;

    @Column(name = "reason", columnDefinition = "TEXT")
    private String reason;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private DiscountRequestStatus status = DiscountRequestStatus.PENDING;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_by_user_id") // The Manager who reviewed the request
    private User reviewedByUser;

    @Column(name = "manager_comments", columnDefinition = "TEXT")
    private String managerComments;

    @Column(name = "approved_date")
    private LocalDateTime approvedDate;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;
}