package com.musketeers.jewelverse.model.entity.discount;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.user.SalesAssistant;
import com.musketeers.jewelverse.model.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "discount_requests")
public class DiscountRequest extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_assistant_id", nullable = false)
    private SalesAssistant salesAssistant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    @Column(name = "requested_discount_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private DiscountType requestedDiscountType;

    @Column(name = "requested_discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal requestedDiscountValue;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "status", nullable = false)
    private String status = "PENDING"; // PENDING, APPROVED, REJECTED

    @Column(name = "manager_comments")
    private String managerComments;

    @Column(name = "approved_date")
    private LocalDateTime approvedDate;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    // Constructors
    public DiscountRequest() {}

    public DiscountRequest(SalesAssistant salesAssistant, Customer customer, Jewelry jewelry,
                           DiscountType requestedDiscountType, BigDecimal requestedDiscountValue, String reason) {
        this.salesAssistant = salesAssistant;
        this.customer = customer;
        this.jewelry = jewelry;
        this.requestedDiscountType = requestedDiscountType;
        this.requestedDiscountValue = requestedDiscountValue;
        this.reason = reason;
    }

    // Helper methods
    public boolean isApproved() {
        return "APPROVED".equals(status);
    }

    public boolean isExpired() {
        return expiresAt != null && LocalDateTime.now().isAfter(expiresAt);
    }

    public boolean isValid() {
        return isApproved() && !isExpired();
    }
}
