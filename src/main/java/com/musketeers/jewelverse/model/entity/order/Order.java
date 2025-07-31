// src/main/java/com/musketeers/jewelverse/model/entity/order/Order.java
// NOTE: This entity has been corrected to include a primary key by extending BaseEntity.

package com.musketeers.jewelverse.model.entity.order;

import com.musketeers.jewelverse.model.entity.BaseEntity;
import com.musketeers.jewelverse.model.entity.payment.Payment;
import com.musketeers.jewelverse.model.entity.user.Customer;
import com.musketeers.jewelverse.model.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class Order extends BaseEntity { // <-- FIX: Extends BaseEntity to get the @Id

    @Column(name = "order_number", unique = true, nullable = false)
    private String orderNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "total_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount = BigDecimal.ZERO;

    @Column(name = "final_amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalAmount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "shipping_address", nullable = false, columnDefinition = "TEXT")
    private String shippingAddress;

    @Column(name = "billing_address", nullable = false, columnDefinition = "TEXT")
    private String billingAddress;

    @Column(name = "order_date", nullable = false)
    private LocalDateTime orderDate;

    @Column(name = "expected_delivery_date")
    private LocalDateTime expectedDeliveryDate;

    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments;

    // Constructors
    public Order() {
        this.orderDate = LocalDateTime.now();
    }

    public Order(String orderNumber, Customer customer, BigDecimal totalAmount, String shippingAddress, String billingAddress) {
        this();
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.totalAmount = totalAmount;
        this.finalAmount = totalAmount;
        this.shippingAddress = shippingAddress;
        this.billingAddress = billingAddress;
    }
}