package com.ciena.inventoryapp.service;

import com.ciena.inventoryapp.model.Device;

import java.util.List;

public interface DeviceService {
  Device saveDevice(Device device);
  Device getDevice(Long id);
  Device modifyDevice(Long id,Device device);
  void deleteDevice(Long id);
  List<Device>getAllDevices();
  void addShelfPositionToDevice(Long deviceId,Long shelfPositionId);
}
