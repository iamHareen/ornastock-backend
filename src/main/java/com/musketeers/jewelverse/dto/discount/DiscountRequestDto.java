// src/main/java/com/musketeers/jewelverse/dto/discount/DiscountRequestDto.java
// DTO for viewing the details of a discount request.

package com.musketeers.jewelverse.dto.discount;

import com.musketeers.jewelverse.model.enums.DiscountRequestStatus;
import lombok.Builder;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class DiscountRequestDto {
    private Long id;
    private String jewelryName;
    private String requestedByUserName;
    private BigDecimal requestedDiscountPercentage;
    private String reason;
    private DiscountRequestStatus status;
    private String reviewedByUserName;
    private String managerComment;
    private LocalDateTime createdAt;
}