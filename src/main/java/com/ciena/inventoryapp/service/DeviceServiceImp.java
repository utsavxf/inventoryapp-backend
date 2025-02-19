package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.DeviceRepository;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //tells Spring to detect this class during component scanning and create a bean for it in the application context.
public class DeviceServiceImp implements DeviceService {

    //importing an object of DeviceRepository
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ShelfPositionRepository shelfPositionRepository;

    @Override
    public Device saveDevice(Device device) {
        System.out.println("Saving device " + device);
        return deviceRepository.save(device);
    }
    @Override
    public Device getDevice(Long id) {
        return deviceRepository.findById(id).orElseThrow(() -> new RuntimeException("Device not found"));
    }

    public Device getDeviceByName(String name){
        return deviceRepository.getDeviceByName(name);
    }

    @Override
    public List<Device> getAllDevices(){
        return deviceRepository.findAll();
    }

    @Override
    public Device modifyDevice(Long id, Device device) {
        Device existingDevice = getDevice(id); // Get the existing device
        existingDevice.setName(device.getName());
        existingDevice.setType(device.getType());
        return deviceRepository.save(existingDevice); // Save updated device
    }

    @Override
    public void deleteDevice(Long id) {
        deviceRepository.deleteById(id);
    }

    @Override
    public void addShelfPositionToDevice(Long deviceId,Long shelfPositionId){
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new RuntimeException("Device not found"));

        ShelfPosition shelfPosition = shelfPositionRepository.findById(shelfPositionId)
                .orElseThrow(() -> new RuntimeException("Shelf Position not found"));

        device.addShelfPosition(shelfPosition); // Add relationship
        shelfPosition.attachDevice(device);
        shelfPositionRepository.save(shelfPosition);
        deviceRepository.save(device); // Save updated device
    }
}
