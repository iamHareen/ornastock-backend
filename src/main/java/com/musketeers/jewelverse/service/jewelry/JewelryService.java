// src/main/java/com/musketeers/jewelverse/service/jewelry/JewelryService.java
// The service interface remains the contract for our business logic.

package com.musketeers.jewelverse.service.jewelry;

import com.musketeers.jewelverse.dto.jewelry.JewelryCreateDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryUpdateDto;
import java.util.List;

public interface JewelryService {
    List<JewelryDto> getAllJewelry();
    JewelryDto getJewelryById(Long id);
    JewelryDto createJewelry(JewelryCreateDto createDto);
    JewelryDto updateJewelry(Long id, JewelryUpdateDto updateDto);
    void deleteJewelry(Long id);
}