package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.service.ShelfPositionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
@RestController
@RequestMapping("/shelfposition")
public class ShelfPositionController {

    // Logger for debugging
    private static final Logger logger = LoggerFactory.getLogger(ShelfPositionController.class);

    private final ShelfPositionService shelfPositionService;

    public ShelfPositionController(ShelfPositionService shelfPositionService) {
        this.shelfPositionService = shelfPositionService;
    }

    @PostMapping("/save")
    public ResponseEntity<ShelfPosition> saveShelfPosition(@RequestBody @Valid ShelfPosition shelfPosition) {
        logger.info("Saving a new shelf position: {}", shelfPosition);
        ShelfPosition savedShelfPosition = shelfPositionService.saveShelfPosition(shelfPosition);
        return ResponseEntity.ok(savedShelfPosition);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelfPosition> getShelfPosition(@PathVariable Long id) {
        logger.info("Fetching shelf position with ID: {}", id);
        ShelfPosition shelfPosition = shelfPositionService.getShelfPosition(id);
        return ResponseEntity.ok(shelfPosition);
    }

    @GetMapping("/getAllShelfPositions")
    public ResponseEntity<List<ShelfPosition>> getAllShelfPositions() {
        logger.info("Fetching all shelf positions");
        List<ShelfPosition> shelfPositions = shelfPositionService.getAllShelfPositions();
        return ResponseEntity.ok(shelfPositions);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ShelfPosition> updateShelfPosition(@PathVariable Long id, @RequestBody @Valid ShelfPosition shelfPosition) {
        logger.info("Updating shelf position with ID: {}", id);
        ShelfPosition updatedShelfPosition = shelfPositionService.updateShelfPosition(id, shelfPosition);
        return ResponseEntity.ok(updatedShelfPosition);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,String>>deleteShelfPosition(@PathVariable Long id) {
        logger.info("Deleting shelf position with ID: {}", id);
        shelfPositionService.deleteShelfPosition(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf Position deleted successfully");
        return ResponseEntity.ok(response);
    }
}
