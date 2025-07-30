// src/main/java/com/musketeers/jewelverse/dto/jewelry/JewelryCreateDto.java
// This DTO is for receiving data from the manager to create a new jewelry item.

package com.musketeers.jewelverse.dto.jewelry;

import com.musketeers.jewelverse.model.enums.JewelryStatus;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class JewelryCreateDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String material;
    private Double weight;
    private String dimensions;
    private String sku;
    private JewelryStatus status = JewelryStatus.ACTIVE;
    private Long categoryId; // We use the ID to link the category
    private Integer initialStock;
    private List<String> imageUrls;
}