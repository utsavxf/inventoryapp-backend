package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import com.ciena.inventoryapp.repository.ShelfRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShelfServiceImp implements ShelfService{

    private final ShelfRepository shelfRepository;
    private final ShelfPositionRepository shelfPositionRepository;

    public ShelfServiceImp(ShelfRepository shelfRepository, ShelfPositionRepository shelfPositionRepository) {
        this.shelfRepository = shelfRepository;
        this.shelfPositionRepository = shelfPositionRepository;
    }


    @Override
    @Transactional
    public Shelf saveShelf(@Valid Shelf shelf) {
        try {
            return shelfRepository.save(shelf);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Shelf with name '" + shelf.getName() + "' already exists.");
        }
    }


    @Override
    public Shelf getShelfById(Long id) {
        return shelfRepository.findById(id).orElseThrow(() -> new RuntimeException("Shelf not found with ID: " + id));
    }

    @Override
    public List<Shelf> getAllShelves() {
        return shelfRepository.findAll();
    }

    @Override
    @Transactional
    public void addShelfToShelfPosition(Long shelfId, Long shelfPositionId) {
        Shelf shelf=shelfRepository.findById(shelfId).orElseThrow(()->new RuntimeException("Shelf not found"));
        ShelfPosition shelfPosition=shelfPositionRepository.findById(shelfPositionId).orElseThrow(()->new RuntimeException("Shelf Position not found"));
        shelf.addShelfPosition(shelfPosition);
        shelfPosition.attachShelf(shelf);
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);
    }

    @Override
    @Transactional
    public Shelf updateShelf(Long id, @Valid Shelf shelf) {
        Shelf currentShelf=getShelfById(id);
        currentShelf.setName(shelf.getName());
        currentShelf.setType(shelf.getType());
        return saveShelf(currentShelf);
    }

    @Override
    public void deleteShelf(Long id) {
        shelfRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void removeShelfFromShelfPosition(Long shelfId, Long shelfPositionId) {
        //first of all fetch respective shelf and shelfPosition
        Shelf shelf=shelfRepository.findById(shelfId).orElseThrow(()->new RuntimeException("Shelf not found"));
        ShelfPosition shelfPosition=shelfPositionRepository.findById(shelfPositionId).orElseThrow(()->new RuntimeException("Shelf Position not found"));
        shelf.setShelfPosition(null);
        shelfPosition.setShelf(null);
        shelfRepository.save(shelf);
        shelfPositionRepository.save(shelfPosition);
    }
}
