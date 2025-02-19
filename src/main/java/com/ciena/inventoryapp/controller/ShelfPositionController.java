package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.service.ShelfPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ShelfPosition saveShelfPosition(@RequestBody ShelfPosition shelfPosition) {
        logger.info("Saving a new shelf position: {}", shelfPosition);
        return shelfPositionService.saveShelfPosition(shelfPosition);
    }

    @GetMapping("/{id}")
    public ShelfPosition getShelfPosition(@PathVariable Long id) {
        logger.info("Fetching shelf position with ID: {}", id);
        return shelfPositionService.getShelfPosition(id);
    }

    @GetMapping("/getAllShelfPositions")
    public List<ShelfPosition> getAllShelfPositions() {
        logger.info("Fetching all shelf positions");
        return shelfPositionService.getAllShelfPositions();
    }

    @PutMapping("/update/{id}")
    public ShelfPosition updateShelfPosition(@PathVariable Long id, @RequestBody ShelfPosition shelfPosition) {
        logger.info("Updating shelf position with ID: {}", id);
        return shelfPositionService.updateShelfPosition(id, shelfPosition);
    }
}
