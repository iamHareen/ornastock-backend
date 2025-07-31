// src/main/java/com/musketeers/jewelverse/service/jewelry/impl/JewelryServiceImpl.java
// The implementation of the service, containing the core logic.

package com.musketeers.jewelverse.service.jewelry.impl;

import com.musketeers.jewelverse.dto.jewelry.JewelryCreateDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryUpdateDto;
import com.musketeers.jewelverse.exception.ResourceNotFoundException;
import com.musketeers.jewelverse.model.entity.jewelry.Category;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.repository.CategoryRepository;
import com.musketeers.jewelverse.repository.JewelryRepository;
import com.musketeers.jewelverse.service.jewelry.JewelryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JewelryServiceImpl implements JewelryService {

    private final JewelryRepository jewelryRepository;
    private final CategoryRepository categoryRepository; // Inject to find categories

    @Override
    @Transactional(readOnly = true)
    public List<JewelryDto> getAllJewelry() {
        return jewelryRepository.findAll().stream()
                .map(Mappers::toJewelryDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public JewelryDto getJewelryById(Long id) {
        return jewelryRepository.findById(id)
                .map(com.musketeers.jewelverse.util.mapper.Mappers::toJewelryDto)
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found with id: " + id));
    }

    @Override
    @Transactional
    public JewelryDto createJewelry(JewelryCreateDto createDto) {
        // Find the category entity from the database using the ID from the DTO
        Category category = categoryRepository.findById(createDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + createDto.getCategoryId()));

        Jewelry newJewelry = com.musketeers.jewelverse.util.mapper.Mappers.toJewelryEntity(createDto, category);
        Jewelry savedJewelry = jewelryRepository.save(newJewelry);
        return com.musketeers.jewelverse.util.mapper.Mappers.toJewelryDto(savedJewelry);
    }

    @Override
    @Transactional
    public JewelryDto updateJewelry(Long id, JewelryUpdateDto updateDto) {
        Jewelry existingJewelry = jewelryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found with id: " + id));

        // Find the category if the ID has changed
        Category category = categoryRepository.findById(updateDto.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + updateDto.getCategoryId()));

        com.musketeers.jewelverse.util.mapper.Mappers.updateJewelryFromDto(existingJewelry, updateDto, category);
        Jewelry updatedJewelry = jewelryRepository.save(existingJewelry);
        return com.musketeers.jewelverse.util.mapper.Mappers.toJewelryDto(updatedJewelry);
    }

    @Override
    @Transactional
    public void deleteJewelry(Long id) {
        if (!jewelryRepository.existsById(id)) {
            throw new ResourceNotFoundException("Jewelry not found with id: " + id);
        }
        jewelryRepository.deleteById(id);
    }
}