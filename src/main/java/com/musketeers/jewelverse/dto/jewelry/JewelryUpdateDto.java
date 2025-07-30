// src/main/java/com/musketeers/jewelverse/dto/jewelry/JewelryUpdateDto.java
// This DTO is for receiving data from the manager to update an existing jewelry item.

package com.musketeers.jewelverse.dto.jewelry;

import com.musketeers.jewelverse.model.enums.JewelryStatus;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class JewelryUpdateDto {
    private String name;
    private String description;
    private BigDecimal price;
    private String material;
    private Double weight;
    private String dimensions;
    private JewelryStatus status;
    private Long categoryId;
}