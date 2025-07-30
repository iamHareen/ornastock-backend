// src/main/java/com/musketeers/jewelverse/util/Mappers.java
// This utility class now correctly handles the complex relationships in your entities.

package com.musketeers.jewelverse.util;

import com.musketeers.jewelverse.dto.jewelry.JewelryCreateDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryUpdateDto;
import com.musketeers.jewelverse.model.entity.jewelry.Category;
import com.musketeers.jewelverse.model.entity.jewelry.Inventory;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.jewelry.JewelryImage;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Mappers {

    // Mapper to convert Jewelry Entity -> JewelryDto
    public static JewelryDto toJewelryDto(Jewelry jewelry) {
        if (jewelry == null) {
            return null;
        }
        return JewelryDto.builder()
                .id(jewelry.getId())
                .name(jewelry.getName())
                .description(jewelry.getDescription())
                .price(jewelry.getPrice())
                .material(jewelry.getMaterial())
                .weight(jewelry.getWeight())
                .dimensions(jewelry.getDimensions())
                .sku(jewelry.getSku())
                .status(jewelry.getStatus())
                .categoryName(jewelry.getCategory() != null ? jewelry.getCategory().getName() : null)
                .stockQuantity(jewelry.getInventory() != null ? jewelry.getInventory().getQuantity() : 0)
                .imageUrls(jewelry.getImages() != null ? jewelry.getImages().stream().map(JewelryImage::getImageUrl).collect(Collectors.toList()) : Collections.emptyList())
                .createdAt(jewelry.getCreatedAt())
                .updatedAt(jewelry.getUpdatedAt())
                .build();
    }

    // Mapper to create a new Jewelry entity from a JewelryCreateDto
    public static Jewelry toJewelryEntity(JewelryCreateDto dto, Category category) {
        Jewelry jewelry = new Jewelry();
        jewelry.setName(dto.getName());
        jewelry.setDescription(dto.getDescription());
        jewelry.setPrice(dto.getPrice());
        jewelry.setMaterial(dto.getMaterial());
        jewelry.setWeight(dto.getWeight());
        jewelry.setDimensions(dto.getDimensions());
        jewelry.setSku(dto.getSku());
        jewelry.setStatus(dto.getStatus());
        jewelry.setCategory(category);

        // Create and associate inventory
        Inventory inventory = new Inventory();
        inventory.setQuantity(dto.getInitialStock());
        inventory.setJewelry(jewelry);
        jewelry.setInventory(inventory);

        // Create and associate images
        if (dto.getImageUrls() != null) {
            List<JewelryImage> images = dto.getImageUrls().stream().map(url -> {
                JewelryImage image = new JewelryImage();
                image.setImageUrl(url);
                image.setJewelry(jewelry);
                return image;
            }).collect(Collectors.toList());
            jewelry.setImages(images);
        }

        return jewelry;
    }

    // Mapper to update an existing Jewelry entity from a JewelryUpdateDto
    public static void updateJewelryFromDto(Jewelry jewelry, JewelryUpdateDto dto, Category category) {
        jewelry.setName(dto.getName());
        jewelry.setDescription(dto.getDescription());
        jewelry.setPrice(dto.getPrice());
        jewelry.setMaterial(dto.getMaterial());
        jewelry.setWeight(dto.getWeight());
        jewelry.setDimensions(dto.getDimensions());
        jewelry.setStatus(dto.getStatus());
        jewelry.setCategory(category);
    }
}