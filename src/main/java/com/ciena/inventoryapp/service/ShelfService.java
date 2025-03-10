package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Shelf;

import java.util.List;

public interface ShelfService {
  Shelf saveShelf(Shelf shelf);
  Shelf getShelfById(Long id);
  List<Shelf> getAllShelves();
  void addShelfToShelfPosition(Long shelfId,Long shelfPositionId);
  Shelf updateShelf(Long id, Shelf shelf);
  void deleteShelf(Long id);
  void removeShelfFromShelfPosition(Long shelfId,Long shelfPositionId);
}
