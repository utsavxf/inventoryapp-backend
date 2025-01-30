package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;  // Mocking the repository

    @InjectMocks
    private DeviceServiceImp deviceService;  // Injecting mocks into service

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks
    }

    @Test
    void testSaveDevice() {
        Device device = new Device(1L, "Router 12","Router");

        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device savedDevice = deviceService.saveDevice(device);

        assertNotNull(savedDevice);
        assertEquals("Router 12", savedDevice.getName());
        verify(deviceRepository, times(1)).save(device);
    }

    @Test
    void testGetDeviceById() {
        Device device = new Device(1L, "Router 12","Router");

        when(deviceRepository.findById(1L)).thenReturn(Optional.of(device));

        Device foundDevice = deviceService.getDevice(1L);

        assertNotNull(foundDevice);
        assertEquals(1L, foundDevice.getId());
        assertEquals("Router 12", foundDevice.getName());
    }

    @Test
    void testGetAllDevices() {
        Device device1 = new Device(1L, "Router 12","Router");
        Device device2 = new Device(2L, "Switch 1","Switch");

        List<Device> devices = Arrays.asList(device1, device2);
        when(deviceRepository.findAll()).thenReturn(devices);

        List<Device> result = deviceService.getAllDevices();

        assertEquals(2, result.size());
        verify(deviceRepository, times(1)).findAll();
    }

    @Test
    void testDeleteDevice() {
        Long deviceId = 1L;
        doNothing().when(deviceRepository).deleteById(deviceId);

        deviceService.deleteDevice(deviceId);

        verify(deviceRepository, times(1)).deleteById(deviceId);
    }
}
