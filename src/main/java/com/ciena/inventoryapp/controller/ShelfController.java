package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.service.DeviceService;
import com.ciena.inventoryapp.service.ShelfService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shelf")
public class ShelfController {
    //logger,creating an instance
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);



    private final ShelfService shelfService;
    //making constructor injection
    public ShelfController(ShelfService shelfService){
        this.shelfService=shelfService;
    }
    @PostMapping("/save")
    public Shelf saveDevice(@RequestBody Shelf shelf){
        logger.info("Saving a new shelf: {}", shelf);
        return shelfService.saveShelf(shelf);
    }
    @GetMapping("/{id}")
    public Shelf getDevice(@PathVariable Long id) {
        logger.info("Fetching shelf with ID: {}", id);
        Shelf shelf = shelfService.getShelfById(id);
        if (shelf == null) {
            logger.warn("Shelf with ID {} not found", id);
        }
        return shelf;
    }

    @GetMapping("/getAllShelves")
    public List<Shelf> getAllDevices() {
        logger.info("Fetching all devices");
        return shelfService.getAllShelves();
    }

    @PostMapping("/{shelfId}/addShelfPosition/{shelfPositionId}")
    public ResponseEntity<String> addShelfToShelfPosition(
            @PathVariable Long shelfId,
            @PathVariable Long shelfPositionId) {
        shelfService.addShelfToShelfPosition(shelfId, shelfPositionId);
        return ResponseEntity.ok("Shelf added to Shelf Position Successfully");
    }
}
