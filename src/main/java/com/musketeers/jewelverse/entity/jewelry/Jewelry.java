package com.musketeers.jewelverse.entity.jewelry;

import com.musketeers.jewelverse.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "jewelries")
public class Jewelry extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @Column(name = "material")
    private String material;

    @Column(name = "weight")
    private Double weight;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "sku", unique = true)
    private String sku;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private JewelryStatus status = JewelryStatus.ACTIVE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "jewelry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JewelryImage> images;

    @OneToOne(mappedBy = "jewelry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Inventory inventory;

    @OneToMany(mappedBy = "jewelry", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Discount> discounts;

    // Constructors
    public Jewelry() {}

    public Jewelry(String name, String description, BigDecimal price, Category category) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
    }

}
