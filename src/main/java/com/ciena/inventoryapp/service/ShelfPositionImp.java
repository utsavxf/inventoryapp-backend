package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShelfPositionImp implements ShelfPositionService {

    //We need to connect with the database
    @Autowired
    ShelfPositionRepository shelfPositionRepository;

    @Override
    public ShelfPosition saveShelfPosition(ShelfPosition shelfPosition) {
     return shelfPositionRepository.save(shelfPosition);
    }

    @Override
    public ShelfPosition getShelfPosition(Long id) {
        return shelfPositionRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    @Override
    public List<ShelfPosition> getAllShelfPositions() {
        return shelfPositionRepository.findAll();
    }
}
