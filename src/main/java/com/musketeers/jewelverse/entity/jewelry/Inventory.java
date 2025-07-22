package com.musketeers.jewelverse.entity.jewelry;

import com.musketeers.jewelverse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "inventory")
public class Inventory extends BaseEntity {

    @Column(name = "quantity", nullable = false)
    private Integer quantity = 0;

    @Column(name = "reserved_quantity")
    private Integer reservedQuantity = 0;

    @Column(name = "minimum_stock_level")
    private Integer minimumStockLevel = 0;

    @Column(name = "warehouse_location")
    private String warehouseLocation;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "jewelry_id", nullable = false)
    private Jewelry jewelry;

    // Constructors
    public Inventory() {}

    public Inventory(Integer quantity, Jewelry jewelry) {
        this.quantity = quantity;
        this.jewelry = jewelry;
    }


    // Helper methods
    public Integer getAvailableQuantity() {
        return quantity - reservedQuantity;
    }

    public boolean isInStock() {
        return getAvailableQuantity() > 0;
    }

    public boolean isLowStock() {
        return quantity <= minimumStockLevel;
    }
}
