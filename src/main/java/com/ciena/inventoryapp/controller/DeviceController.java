package com.ciena.inventoryapp.controller;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // Allow requests from this origin
@RestController
@RequestMapping("/device")
public class DeviceController {

    //logger,creating an instance
    private static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService){
        this.deviceService=deviceService;
    }

    @PostMapping("/save")
    public ResponseEntity<Device> saveDevice(@RequestBody @Valid Device device) {
        logger.info("Saving a new device: {}", device);
        Device savedDevice = deviceService.saveDevice(device);
        return ResponseEntity.ok(savedDevice);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDevice(@PathVariable Long id) {
        logger.info("Fetching device with ID: {}", id);
        Device device = deviceService.getDevice(id);
        return ResponseEntity.ok(device);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<Device> getDeviceByName(@PathVariable String name) {
        logger.info("Fetching device with name: {}", name);
        Device device = deviceService.getDeviceByName(name);
        return ResponseEntity.ok(device);
    }

    @GetMapping("/getAllDevices")
    public ResponseEntity<List<Device>> getAllDevices() {
        logger.info("Fetching all devices");
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Device> modifyDevice(@PathVariable Long id, @RequestBody @Valid Device updatedDevice) {
        logger.info("Modifying device with ID: {}", id);
        Device modifiedDevice = deviceService.modifyDevice(id, updatedDevice);
        return ResponseEntity.ok(modifiedDevice);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String, String>> deleteDevice(@PathVariable Long id) {
        logger.info("Deleting device with ID: {}", id);
        deviceService.deleteDevice(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Device deleted successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{deviceId}/addShelfPosition/{shelfPositionId}")
    public ResponseEntity<Map<String, String>> addShelfPositionToDevice(
            @PathVariable Long deviceId,
            @PathVariable Long shelfPositionId) {
        logger.info("Adding Shelf Position to Device: deviceId={}, shelfPositionId={}", deviceId, shelfPositionId);
        deviceService.addShelfPositionToDevice(deviceId, shelfPositionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf Position added to device successfully");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{deviceId}/removeShelfPosition/{shelfPositionId}")
    public ResponseEntity<Map<String,String>> removeShelfPositionFromDevice(@PathVariable Long deviceId,@PathVariable Long shelfPositionId){
        deviceService.removeShelfPositionFromDevice(deviceId, shelfPositionId);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Shelf Position removed from device successfully");
        return ResponseEntity.ok(response); // Return a JSON object
    }



}





