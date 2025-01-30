package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.ShelfRepository;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShelfServiceTest {

    @Mock //It creates a fake (mocked) version of a dependency, so you don’t need a real database.
    private ShelfRepository shelfRepository;

    @Mock //similar it creates a fake version of shelfPositionRepository
    private ShelfPositionRepository shelfPositionRepository;

    @InjectMocks //It automatically injects the mocked dependencies (@Mock) into the class under test
    //making object of the class whose methods we want to test which is ShelfService in this case
    private ShelfServiceImp shelfService;


    //WHAT WILL HAPPEN IF WE DON'T USE INJECT MONK ANNOTATION
   //The test won't have ShelfRepository and ShelfPositionRepository injected, and the service methods may throw NullPointerException.

    @BeforeEach //This method runs before each test case to initialize mocks using MockitoAnnotations.openMocks(this);.
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveShelf() {
        Shelf shelf = new Shelf();
        shelf.setId(1L);
        shelf.setName("Test Shelf");

        when(shelfRepository.save(any(Shelf.class))).thenReturn(shelf);

        Shelf savedShelf = shelfService.saveShelf(shelf);

        assertNotNull(savedShelf); //Ensures the shelf is not NULL
        assertEquals("Test Shelf", savedShelf.getName());
        verify(shelfRepository, times(1)).save(shelf); //Ensures method save() is called exactly once
    }

    @Test
    void testGetShelfById() {
        Shelf shelf = new Shelf();
        shelf.setId(1L);
        shelf.setName("Test Shelf");

        when(shelfRepository.findById(1L)).thenReturn(Optional.of(shelf));

        Shelf foundShelf = shelfService.getShelfById(1L);

        assertNotNull(foundShelf);
        assertEquals(1L, foundShelf.getId());
        assertEquals("Test Shelf", foundShelf.getName());
    }

    @Test
    void testAddShelfToShelfPosition() {
        Shelf shelf = new Shelf();
        shelf.setId(1L);

        ShelfPosition shelfPosition = new ShelfPosition();
        shelfPosition.setId(100L);

        when(shelfRepository.findById(1L)).thenReturn(Optional.of(shelf));
        when(shelfPositionRepository.findById(100L)).thenReturn(Optional.of(shelfPosition));
        when(shelfRepository.save(any(Shelf.class))).thenReturn(shelf);

        shelfService.addShelfToShelfPosition(1L, 100L);

        assertEquals(100L, shelf.getPositionId());
        verify(shelfRepository, times(1)).save(shelf);
    }
}
