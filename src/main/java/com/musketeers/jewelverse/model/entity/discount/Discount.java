package com.musketeers.jewelverse.model.entity.discount;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.enums.DiscountType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "discounts")
public class Discount extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DiscountType type;

    @Column(name = "discount_value", nullable = false, precision = 10, scale = 2)
    private BigDecimal value;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive = true;

    @Column(name = "minimum_order_amount", precision = 10, scale = 2)
    private BigDecimal minimumOrderAmount;

    @Column(name = "maximum_discount_amount", precision = 10, scale = 2)
    private BigDecimal maximumDiscountAmount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id")
    private Jewelry jewelry;

    // Constructors
    public Discount() {}

    public Discount(String name, DiscountType type, BigDecimal value, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Helper methods
    public boolean isValid() {
        LocalDateTime now = LocalDateTime.now();
        return isActive && now.isAfter(startDate) && now.isBefore(endDate);
    }

    public BigDecimal calculateDiscount(BigDecimal amount) {
        if (!isValid()) {
            return BigDecimal.ZERO;
        }

        BigDecimal discountAmount;
        if (type == DiscountType.PERCENTAGE) {
            discountAmount = amount.multiply(value).divide(BigDecimal.valueOf(100));
        } else {
            discountAmount = value;
        }

        if (maximumDiscountAmount != null && discountAmount.compareTo(maximumDiscountAmount) > 0) {
            discountAmount = maximumDiscountAmount;
        }

        return discountAmount;
    }
}
