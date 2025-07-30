// src/main/java/com/musketeers/jewelverse/dto/discount/DiscountRequestCreateDto.java
// DTO for the Sales Assistant to send when creating a request.

package com.musketeers.jewelverse.dto.discount;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class DiscountRequestCreateDto {
    private Long jewelryId;
    private Long salesAssistantId; // In a real app, this would come from the authenticated user's token
    private BigDecimal requestedDiscountPercentage;
    private String reason;
}