// src/main/java/com/musketeers/jewelverse/dto/jewelry/JewelryDto.java
// This DTO is for sending jewelry data to the frontend. It's a "view" model.

package com.musketeers.jewelverse.dto.jewelry;

import com.musketeers.jewelverse.model.enums.JewelryStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class JewelryDto {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String material;
    private Double weight;
    private String dimensions;
    private String sku;
    private JewelryStatus status;
    private String categoryName;
    private Integer stockQuantity;
    private List<String> imageUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
