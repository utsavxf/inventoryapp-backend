package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.Shelf;
import com.ciena.inventoryapp.service.ShelfService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class) //THIS TELLS JUNIT 5 THAT SPRING SHOULD HANDLE THE TESTS
@WebMvcTest(ShelfController.class) //THIS SETUPS THE MVC testing environment,without it test would try to load the full application context,making it slow
public class ShelfControllerTests {

    @Autowired
    private MockMvc mockMvc; //used to simulate HTTP requests without actually starting the server

    @Mock
    private ShelfService shelfService;

    @Test
    void testGetShelfById() throws Exception {
        //creating a fake shelf object
        Shelf shelf = new Shelf();
        shelf.setId(1L);
        shelf.setName("Test Shelf");

        //This ensures that when the controller calls shelfService.getShelfById(1L), it gets the fake data instead of calling the real service.
        when(shelfService.getShelfById(1L)).thenReturn(shelf);

        //simulate get request to our fake http server and then validates the response
        mockMvc.perform(get("/shelf/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Shelf"));
    }
}
