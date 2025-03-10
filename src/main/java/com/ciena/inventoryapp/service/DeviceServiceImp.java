package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Device;
import com.ciena.inventoryapp.model.ShelfPosition;
import com.ciena.inventoryapp.repository.DeviceRepository;
import com.ciena.inventoryapp.repository.ShelfPositionRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service //tells Spring to detect this class during component scanning and create a bean for it in the application context.
public class DeviceServiceImp implements DeviceService {

    //importing an object of DeviceRepository

    private final DeviceRepository deviceRepository;
    private final ShelfPositionRepository shelfPositionRepository;

    //CONSTRUCTOR INJECTION
    public DeviceServiceImp(DeviceRepository deviceRepository, ShelfPositionRepository shelfPositionRepository) {
        this.deviceRepository=deviceRepository;
        this.shelfPositionRepository=shelfPositionRepository;
    }

    @Override
    @Transactional
    public Device saveDevice(@Valid Device device) {
        try{
            return deviceRepository.save(device);
        }catch (Exception e){
            throw  new DataIntegrityViolationException("Device with name: "+device.getName()+" already exists");
        }

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
    @Transactional
    public Device modifyDevice(Long id, @Valid Device device) {
        Device existingDevice = getDevice(id); // Get the existing device
        existingDevice.setName(device.getName());
        existingDevice.setType(device.getType());
        return saveDevice(existingDevice); // Save updated device
    }

    @Override
    @Transactional
    public void deleteDevice(Long id) {
        Device device=getDevice(id); //ensures that device exists
        deviceRepository.deleteById(id);
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public void removeShelfPositionFromDevice(Long deviceId,Long shelfPositionId){
        Device device=deviceRepository.findById(deviceId).orElseThrow(()->new RuntimeException("Device not found"));
        ShelfPosition shelfPosition=shelfPositionRepository.findById(shelfPositionId).orElseThrow(()->new RuntimeException("Shelf Position not found"));

        //removing the shelfPosition
        device.getShelfPositions().removeIf(sp->sp.getId().equals(shelfPositionId));
        //setting the device to null
        shelfPosition.setDevice(null);


        shelfPositionRepository.save(shelfPosition);
        deviceRepository.save(device);
    }


}





