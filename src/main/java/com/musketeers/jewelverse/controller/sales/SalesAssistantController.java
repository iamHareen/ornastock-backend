// src/main/java/com/musketeers/jewelverse/controller/sales/SalesAssistantController.java
// The REST controller for Sales Assistant actions.

package com.musketeers.jewelverse.controller.sales;

import com.musketeers.jewelverse.dto.discount.DiscountRequestCreateDto;
import com.musketeers.jewelverse.dto.discount.DiscountRequestDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.service.discount.DiscountRequestService;
import com.musketeers.jewelverse.service.jewelry.JewelryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
@RequiredArgsConstructor
public class SalesAssistantController {

    private final JewelryService jewelryService;
    private final DiscountRequestService discountRequestService;

    // Endpoint for Sales Assistant to view all available jewelry and their stock
    @GetMapping("/jewelry")
    public ResponseEntity<List<JewelryDto>> viewAllJewelry() {
        List<JewelryDto> jewelryList = jewelryService.getAllJewelry();
        return ResponseEntity.ok(jewelryList);
    }

    // Endpoint for Sales Assistant to view a single jewelry item
    @GetMapping("/jewelry/{id}")
    public ResponseEntity<JewelryDto> viewJewelryById(@PathVariable Long id) {
        JewelryDto jewelry = jewelryService.getJewelryById(id);
        return ResponseEntity.ok(jewelry);
    }

    // Endpoint for Sales Assistant to request a special discount
    @PostMapping("/discounts/request")
    public ResponseEntity<DiscountRequestDto> requestSpecialDiscount(@RequestBody DiscountRequestCreateDto createDto) {
        DiscountRequestDto createdRequest = discountRequestService.createDiscountRequest(createDto);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }
}