package com.musketeers.jewelverse.util.mapper;

import com.musketeers.jewelverse.dto.jewelry.JewelryCreateDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryUpdateDto;
import com.musketeers.jewelverse.model.entity.jewelry.Category;
import com.musketeers.jewelverse.model.entity.jewelry.Inventory;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.jewelry.JewelryImage;

import java.util.List;
import java.util.stream.Collectors;

public class JewelryMapper {

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
                .imageUrls(mapImageUrls(jewelry.getImages()))
                .createdAt(jewelry.getCreatedAt())
                .updatedAt(jewelry.getUpdatedAt())
                .build();
    }

    public static Jewelry toJewelryEntity(JewelryCreateDto createDto, Category category) {
        if (createDto == null) {
            return null;
        }

        Jewelry jewelry = new Jewelry();
        jewelry.setName(createDto.getName());
        jewelry.setDescription(createDto.getDescription());
        jewelry.setPrice(createDto.getPrice());
        jewelry.setMaterial(createDto.getMaterial());
        jewelry.setWeight(createDto.getWeight());
        jewelry.setDimensions(createDto.getDimensions());
        jewelry.setSku(createDto.getSku());
        jewelry.setStatus(createDto.getStatus());
        jewelry.setCategory(category);

        // Create and set inventory if initial stock is provided
        if (createDto.getInitialStock() != null) {
            Inventory inventory = new Inventory();
            inventory.setQuantity(createDto.getInitialStock());
            inventory.setJewelry(jewelry);
            jewelry.setInventory(inventory);
        }

        return jewelry;
    }

    public static void updateJewelryFromDto(Jewelry jewelry, JewelryUpdateDto updateDto, Category category) {
        if (jewelry == null || updateDto == null) {
            return;
        }

        if (updateDto.getName() != null) {
            jewelry.setName(updateDto.getName());
        }
        if (updateDto.getDescription() != null) {
            jewelry.setDescription(updateDto.getDescription());
        }
        if (updateDto.getPrice() != null) {
            jewelry.setPrice(updateDto.getPrice());
        }
        if (updateDto.getMaterial() != null) {
            jewelry.setMaterial(updateDto.getMaterial());
        }
        if (updateDto.getWeight() != null) {
            jewelry.setWeight(updateDto.getWeight());
        }
        if (updateDto.getDimensions() != null) {
            jewelry.setDimensions(updateDto.getDimensions());
        }
        if (updateDto.getStatus() != null) {
            jewelry.setStatus(updateDto.getStatus());
        }
        if (category != null) {
            jewelry.setCategory(category);
        }
    }

    private static List<String> mapImageUrls(List<JewelryImage> images) {
        if (images == null || images.isEmpty()) {
            return List.of();
        }
        return images.stream()
                .map(JewelryImage::getImageUrl)
                .collect(Collectors.toList());
    }
}
