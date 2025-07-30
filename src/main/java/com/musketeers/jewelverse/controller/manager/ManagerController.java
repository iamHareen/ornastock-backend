// src/main/java/com/musketeers/jewelverse/controller/manager/ManagerController.java
// This controller is specifically for actions a Manager can take.

package com.musketeers.jewelverse.controller.manager;

import com.musketeers.jewelverse.dto.jewelry.JewelryCreateDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryDto;
import com.musketeers.jewelverse.dto.jewelry.JewelryUpdateDto;
import com.musketeers.jewelverse.service.jewelry.JewelryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/manager/jewelry")
@RequiredArgsConstructor
public class ManagerController {

    private final JewelryService jewelryService;

    @PostMapping
    public ResponseEntity<JewelryDto> createJewelryItem(@RequestBody JewelryCreateDto createDto) {
        JewelryDto createdJewelry = jewelryService.createJewelry(createDto);
        return new ResponseEntity<>(createdJewelry, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<JewelryDto> updateJewelryItem(@PathVariable Long id, @RequestBody JewelryUpdateDto updateDto) {
        JewelryDto updatedJewelry = jewelryService.updateJewelry(id, updateDto);
        return ResponseEntity.ok(updatedJewelry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJewelryItem(@PathVariable Long id) {
        jewelryService.deleteJewelry(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<JewelryDto>> getAllJewelryItems() {
        return ResponseEntity.ok(jewelryService.getAllJewelry());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JewelryDto> getJewelryItemById(@PathVariable Long id) {
        return ResponseEntity.ok(jewelryService.getJewelryById(id));
    }
}
