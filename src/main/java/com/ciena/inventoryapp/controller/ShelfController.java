package com.ciena.inventoryapp.controller;


import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.service.ShelfService;
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
@RequestMapping("/shelf")
public class ShelfController {
    //logger,creating an instance
    private static final Logger logger = LoggerFactory.getLogger(ShelfController.class);

    private final ShelfService shelfService;
    //making constructor injection
    public ShelfController(ShelfService shelfService){
        this.shelfService=shelfService;
    }


    @PostMapping("/save")
    public ResponseEntity<Shelf> saveShelf(@RequestBody @Valid Shelf shelf) {
        logger.info("Saving a new shelf: {}", shelf);
        Shelf savedShelf = shelfService.saveShelf(shelf);
        return ResponseEntity.ok(savedShelf);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Shelf> getShelf(@PathVariable Long id) {
        logger.info("Fetching shelf with ID: {}", id);
        Shelf shelf = shelfService.getShelfById(id);
        return ResponseEntity.ok(shelf);
    }

    @GetMapping("/getAllShelves")
    public ResponseEntity<List<Shelf>> getAllShelves() {
        logger.info("Fetching all shelves");
        List<Shelf> shelves = shelfService.getAllShelves();
        return ResponseEntity.ok(shelves);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Shelf> updateShelf(@PathVariable Long id, @RequestBody @Valid Shelf shelf) {
        logger.info("Updating shelf with ID: {}", id);
        Shelf updatedShelf = shelfService.updateShelf(id, shelf);
        return ResponseEntity.ok(updatedShelf);
    }

    @PostMapping("/{shelfId}/addShelfPosition/{shelfPositionId}")
    public ResponseEntity<Map<String, String>> addShelfToShelfPosition(
            @PathVariable Long shelfId,
            @PathVariable Long shelfPositionId) {
        shelfService.addShelfToShelfPosition(shelfId, shelfPositionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf added to Shelf Position successfully");
        return ResponseEntity.ok(response); // Return a JSON object
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteShelfFromShelfPosition(@PathVariable Long id) {
        logger.info("Deleting shelf from Shelf Position: {}", id);
        shelfService.deleteShelf(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf deleted successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{shelfId}/removeShelfPosition/{shelfPositionId}")
    public ResponseEntity<Map<String, String>> removeShelfFromShelfPosition(@PathVariable Long shelfId,@PathVariable Long shelfPositionId) {
        logger.info("Removing shelf from Shelf Position: {}", shelfPositionId);
        shelfService.removeShelfFromShelfPosition(shelfId, shelfPositionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf Position removed from shelf successfully");
        return ResponseEntity.ok(response); // Return a JSON object
    }


}
