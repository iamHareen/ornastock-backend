// src/main/java/com/musketeers/jewelverse/service/discount/DiscountRequestService.java
// The service interface for discount request logic.

package com.musketeers.jewelverse.service.discount;

import com.musketeers.jewelverse.dto.discount.DiscountRequestCreateDto;
import com.musketeers.jewelverse.dto.discount.DiscountRequestDto;
import java.util.List;

public interface DiscountRequestService {
    DiscountRequestDto createDiscountRequest(DiscountRequestCreateDto createDto);
    List<DiscountRequestDto> getAllPendingRequests(); // For the manager to view
}