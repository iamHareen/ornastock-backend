package com.musketeers.jewelverse.model.entity.order;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal unitPrice;

    // Constructors
    public CartItem() {}

    public CartItem(Cart cart, Jewelry jewelry, Integer quantity, BigDecimal unitPrice) {
        this.cart = cart;
        this.jewelry = jewelry;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    // Helper methods
    public BigDecimal getSubTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}