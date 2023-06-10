package com.fyp.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.cm.model.Device;
import com.fyp.cm.response.CustomResponse;
import com.fyp.cm.service.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

   @GetMapping
    public ResponseEntity<CustomResponse<List<Device>>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        CustomResponse<List<Device>> response = new CustomResponse<>(HttpStatus.OK.value(), "Devices retrieved successfully", devices);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Device>> getDeviceById(@PathVariable String id) {
        Device device = deviceService.getDeviceById(id);
        CustomResponse<Device> response;
        
        if (device != null) {
            response = new CustomResponse<>(HttpStatus.OK.value(), "Device retrieved successfully", device);
            return ResponseEntity.ok(response);
        } else {
            response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "Device not found", null);
           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Device>> addDevice(@RequestBody Device device) {
        Device savedDevice = deviceService.addDevice(device);
        CustomResponse<Device> response = new CustomResponse<>(HttpStatus.CREATED.value(), "Device added successfully", savedDevice);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Device>> updateDevice(@PathVariable String id, @RequestBody Device device) {
        Device updatedDevice = deviceService.updateDevice(id, device);
        CustomResponse<Device> response;
        
        if (updatedDevice != null) {
            response = new CustomResponse<>(HttpStatus.OK.value(), "Device updated successfully", updatedDevice);
            return ResponseEntity.ok(response);
        } else {
            response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "Device not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteDevice(@PathVariable String id) {
        Boolean deleted = deviceService.deleteDevice(id);
        CustomResponse<String> response;
        
        if (deleted) {
            response = new CustomResponse<>(HttpStatus.OK.value(), "Device deleted successfully", id + " deleted successfully");
            return ResponseEntity.ok(response);
        } else {
            response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "Device not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
