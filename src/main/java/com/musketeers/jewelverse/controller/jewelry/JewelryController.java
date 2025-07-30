// src/main/java/com/musketeers/jewelverse/controller/jewelry/JewelryController.java
// The REST controller for public jewelry browsing.

package com.musketeers.jewelverse.controller.jewelry;

import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.service.jewelry.JewelryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/jewelry") // Public endpoint, no /customer needed
@RequiredArgsConstructor
public class JewelryController {

    private final JewelryService jewelryService;

    @GetMapping
    public ResponseEntity<List<JewelryDto>> getAllJewelryForDisplay() {
        return ResponseEntity.ok(jewelryService.getAllJewelry());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JewelryDto> getJewelryByIdForDisplay(@PathVariable Long id) {
        return ResponseEntity.ok(jewelryService.getJewelryById(id));
    }
}