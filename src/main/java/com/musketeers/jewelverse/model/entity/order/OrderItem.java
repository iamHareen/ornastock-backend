// src/main/java/com/musketeers/jewelverse/model/entity/order/OrderItem.java
// NEW FILE: This entity defines an item within an order.

package com.musketeers.jewelverse.model.entity.order;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "order_items")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class OrderItem extends BaseEntity { // <-- FIX: Extends BaseEntity to get the @Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "price_at_purchase", nullable = false, precision = 10, scale = 2)
    private BigDecimal priceAtPurchase; // The price of the item when the order was placed

    public OrderItem(Order order, Jewelry jewelry, Integer quantity, BigDecimal priceAtPurchase) {
        this.order = order;
        this.jewelry = jewelry;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }
}
