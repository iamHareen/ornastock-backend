// src/main/java/com/musketeers/jewelverse/service/discount/impl/DiscountRequestServiceImpl.java
// The implementation of the service.

package com.musketeers.jewelverse.service.discount.impl;

import com.musketeers.jewelverse.dto.discount.DiscountRequestCreateDto;
import com.musketeers.jewelverse.dto.discount.DiscountRequestDto;
import com.musketeers.jewelverse.exception.ResourceNotFoundException;
import com.musketeers.jewelverse.model.entity.discount.DiscountRequest;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.user.User;
import com.musketeers.jewelverse.model.enums.DiscountRequestStatus;
import com.musketeers.jewelverse.repository.DiscountRequestRepository;
import com.musketeers.jewelverse.repository.JewelryRepository;
import com.musketeers.jewelverse.repository.UserRepository;
import com.musketeers.jewelverse.service.discount.DiscountRequestService;
import com.musketeers.jewelverse.util.mapper.DiscountRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiscountRequestServiceImpl implements DiscountRequestService {

    private final DiscountRequestRepository discountRequestRepository;
    private final JewelryRepository jewelryRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public DiscountRequestDto createDiscountRequest(DiscountRequestCreateDto createDto) {
        // Find the related entities
        Jewelry jewelry = jewelryRepository.findById(createDto.getJewelryId())
                .orElseThrow(() -> new ResourceNotFoundException("Jewelry not found with id: " + createDto.getJewelryId()));

        User salesAssistant = userRepository.findById(createDto.getSalesAssistantId())
                .orElseThrow(() -> new ResourceNotFoundException("Sales Assistant not found with id: " + createDto.getSalesAssistantId()));

        // TODO: Add logic to check if the user role is actually SALES_ASSISTANT

        DiscountRequest newRequest = DiscountRequestMapper.toDiscountRequestEntity(createDto, jewelry, salesAssistant);
        DiscountRequest savedRequest = discountRequestRepository.save(newRequest);

        return DiscountRequestMapper.toDiscountRequestDto(savedRequest);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiscountRequestDto> getAllPendingRequests() {
        return discountRequestRepository.findByStatus(DiscountRequestStatus.PENDING)
                .stream()
                .map(DiscountRequestMapper::toDiscountRequestDto)
                .collect(Collectors.toList());
    }
}