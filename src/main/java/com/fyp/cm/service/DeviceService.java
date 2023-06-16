package com.fyp.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fyp.cm.model.Device;

@Service
public interface DeviceService {
    List<Device> getAllDevices();
    Device addDevice(Device device);
    Device updateDevice(String id, Device device);
    boolean deleteDevice(String id);
    Device getDeviceById(String id);
    List<Device> findAllByIsInstalled();
}
