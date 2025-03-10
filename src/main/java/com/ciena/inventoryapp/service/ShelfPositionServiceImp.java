package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfPositionServiceImp implements ShelfPositionService {

    //We need to connect with the database
    private final ShelfPositionRepository shelfPositionRepository;
    //constructor injection
    public ShelfPositionServiceImp(ShelfPositionRepository shelfPositionRepository) {
        this.shelfPositionRepository = shelfPositionRepository;
    }

    @Override
    @Transactional
    public ShelfPosition saveShelfPosition(ShelfPosition shelfPosition) {
        try {
            return shelfPositionRepository.save(shelfPosition);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException("Shelf Position with name '" + shelfPosition.getName() + "' already exists.");
        }
    }

    @Override
    public ShelfPosition getShelfPosition(Long id) {
        return shelfPositionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Shelf Position not found with ID: " + id));
    }

    @Override
    public List<ShelfPosition> getAllShelfPositions() {
        return shelfPositionRepository.findAll();
    }

    @Override
    @Transactional
    public ShelfPosition updateShelfPosition(Long id,ShelfPosition shelfPosition) {
        ShelfPosition existingShelfPositon = getShelfPosition(id);
        existingShelfPositon.setName(shelfPosition.getName());
        return saveShelfPosition(existingShelfPositon);

    }

    @Override
    @Transactional
    public void deleteShelfPosition(Long id) {
        if (!shelfPositionRepository.existsById(id)) {
            throw new RuntimeException("Shelf Position not found with ID: " + id);
        }
        shelfPositionRepository.deleteById(id);
    }


}
