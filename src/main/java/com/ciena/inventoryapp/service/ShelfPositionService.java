package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.model.ShelfPosition;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ShelfPositionService {
     ShelfPosition saveShelfPosition(ShelfPosition shelfPosition);
     ShelfPosition getShelfPosition(Long id);
     List<ShelfPosition> getAllShelfPositions();
     ShelfPosition updateShelfPosition(Long id,ShelfPosition shelfPosition);
     void deleteShelfPosition(Long id);
}
