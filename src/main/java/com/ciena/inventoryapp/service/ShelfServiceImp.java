package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import com.ciena.inventoryapp.repository.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShelfServiceImp implements ShelfService{

    @Autowired
    ShelfRepository shelfRepository;
    @Autowired
    ShelfPositionRepository shelfPositionRepository;

    @Override
    public Shelf saveShelf(Shelf shelf) {
        return shelfRepository.save(shelf);
    }

    @Override
    public Shelf getShelfById(Long id) {
        return shelfRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    @Override
    public List<Shelf> getAllShelves() {
        return shelfRepository.findAll();
    }

    @Override
    public void addShelfToShelfPosition(Long shelfId, Long shelfPositionId) {
        Shelf shelf=shelfRepository.findById(shelfId).orElseThrow(()->new RuntimeException("Shelf not found"));
        ShelfPosition shelfPosition=shelfPositionRepository.findById(shelfPositionId).orElseThrow(()->new RuntimeException("Shelf Position not found"));
        shelf.addShelf(shelfPosition);
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);
    }
}
