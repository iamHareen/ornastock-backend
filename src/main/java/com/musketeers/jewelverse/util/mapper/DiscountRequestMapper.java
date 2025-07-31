package com.musketeers.jewelverse.util.mapper;

import com.musketeers.jewelverse.dto.discount.DiscountRequestCreateDto;
import com.musketeers.jewelverse.dto.discount.DiscountRequestDto;
import com.musketeers.jewelverse.model.entity.discount.DiscountRequest;
import com.musketeers.jewelverse.model.entity.jewelry.Jewelry;
import com.musketeers.jewelverse.model.entity.user.User;
import com.musketeers.jewelverse.model.enums.DiscountRequestStatus;
import com.musketeers.jewelverse.model.enums.DiscountType;

public class DiscountRequestMapper {

    public static DiscountRequestDto toDiscountRequestDto(DiscountRequest discountRequest) {
        if (discountRequest == null) {
            return null;
        }

        return DiscountRequestDto.builder()
                .id(discountRequest.getId())
                .jewelryName(discountRequest.getJewelry() != null ? discountRequest.getJewelry().getName() : null)
                .requestedByUserName(getUserFullName(discountRequest.getRequestedByUser()))
                .requestedDiscountPercentage(discountRequest.getRequestedDiscountValue())
                .reason(discountRequest.getReason())
                .status(discountRequest.getStatus())
                .reviewedByUserName(getUserFullName(discountRequest.getReviewedByUser()))
                .managerComment(discountRequest.getManagerComments())
                .createdAt(discountRequest.getCreatedAt())
                .build();
    }

    public static DiscountRequest toDiscountRequestEntity(DiscountRequestCreateDto createDto, Jewelry jewelry, User salesAssistant) {
        if (createDto == null) {
            return null;
        }

        DiscountRequest discountRequest = new DiscountRequest();
        discountRequest.setJewelry(jewelry);
        discountRequest.setRequestedByUser(salesAssistant);
        discountRequest.setRequestedDiscountType(DiscountType.PERCENTAGE); // Default to percentage
        discountRequest.setRequestedDiscountValue(createDto.getRequestedDiscountPercentage());
        discountRequest.setReason(createDto.getReason());
        discountRequest.setStatus(DiscountRequestStatus.PENDING);

        return discountRequest;
    }

    private static String getUserFullName(User user) {
        if (user == null) {
            return null;
        }
        return user.getFirstName() + " " + user.getLastName();
    }
}
