package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.service.DeviceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    //logger,creating an instance
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);



    private final DeviceService deviceService;
    //making constructor injection
    public DeviceController(DeviceService deviceService){
        this.deviceService=deviceService;
    }
    @PostMapping("/save")
    public Device saveDevice(@RequestBody Device device){
        logger.info("Saving a new device: {}", device);
        return deviceService.saveDevice(device);
    }
    @GetMapping("/{id}")
    public Device getDevice(@PathVariable Long id) {
        logger.info("Fetching device with ID: {}", id);
        Device device = deviceService.getDevice(id);
        if (device == null) {
            logger.warn("Device with ID {} not found", id);
        }
        return device;
    }

    @GetMapping("/getAllDevices")
    public List<Device> getAllDevices() {
        logger.info("Fetching all devices");
        return deviceService.getAllDevices();
    }

    @PutMapping("/modify/{id}")
    public Device modifyDevice(@PathVariable Long id, @RequestBody Device updatedDevice) {
        logger.info("Modifying device with ID: {}", id);
        return deviceService.modifyDevice(id, updatedDevice);

    }

    @DeleteMapping("/delete/{id}")
    public boolean deleteDevice(@PathVariable Long id) {
        logger.info("Deleting device with ID: {}", id);
        deviceService.deleteDevice(id);
        return true;
    }

    @PostMapping("/{deviceId}/addShelfPosition/{shelfPositionId}")
    public ResponseEntity<String> addShelfPositionToDevice(
            @PathVariable Long deviceId,
            @PathVariable Long shelfPositionId) {
        deviceService.addShelfPositionToDevice(deviceId, shelfPositionId);
        return ResponseEntity.ok("Shelf position added to device successfully");
    }
}
