package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.service.ShelfPositionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelfpositions")
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

    @GetMapping("/all")
    public List<ShelfPosition> getAllShelfPositions() {
        logger.info("Fetching all shelf positions");
        return shelfPositionService.getAllShelfPositions();
    }
}
