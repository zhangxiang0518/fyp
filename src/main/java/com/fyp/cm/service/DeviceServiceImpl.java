package com.fyp.cm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.cm.exception.ResourceNotFoundException;
import com.fyp.cm.model.Device;
import com.fyp.cm.repo.DeviceRepo;
@Service
public class DeviceServiceImpl implements DeviceService{
    @Autowired
    DeviceRepo deviceRepo;

    public List<Device> getAllDevices() {
        return deviceRepo.findAll();
    }
    public Device addDevice(Device device) {
        // Validate the device properties
        validateDevice(device);
        Device newDevice = Device.builder()
            .serialNum(device.getSerialNum())
            .type(device.getType())
            .isInstalled(device.getIsInstalled())
            .state(device.getState())
            .build();
        return deviceRepo.save(newDevice);
    }
    
    private void validateDevice(Device deviceDto) {
        if (deviceDto.getSerialNum() == null || deviceDto.getSerialNum().isEmpty()) {
            throw new IllegalArgumentException("Serial number cannot be null or empty");
        }
        if (deviceDto.getType() == null || deviceDto.getType().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
        if (deviceDto.getIsInstalled() == null) {
            throw new IllegalArgumentException("IsInstalled cannot be null");
        }
        if (deviceDto.getState() == null || deviceDto.getState().isEmpty()) {
            throw new IllegalArgumentException("State cannot be null or empty");
        }
    }

    public Device updateDevice(String id, Device device) {
        Device existingDevice = getDeviceById(id);
        if (existingDevice != null) {
            device.setId(existingDevice.getId());
            device.setCreatedDate(existingDevice.getCreatedDate());
            return deviceRepo.save(device);
        } else {
            return null;
        }
    }

    public boolean deleteDevice(String id) {
        Device existingDevice = getDeviceById(id);
        if (existingDevice != null) {
            deviceRepo.delete(existingDevice);
            return true;
        } else {
            return false;
        }
    }

    public Device getDeviceById(String id) {
        return deviceRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Device not found with id: " + id));
    }
}
