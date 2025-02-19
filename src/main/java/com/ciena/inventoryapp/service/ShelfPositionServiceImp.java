package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShelfPositionServiceImp implements ShelfPositionService {

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

    @Override
    public ShelfPosition updateShelfPosition(Long id,ShelfPosition shelfPosition) {
        ShelfPosition existingShelfPositon = getShelfPosition(id);
        existingShelfPositon.setName(shelfPosition.getName());
        return shelfPositionRepository.save(existingShelfPositon);

    }


}
