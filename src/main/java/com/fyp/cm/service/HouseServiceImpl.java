package com.fyp.cm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fyp.cm.dto.HouseDto;
import com.fyp.cm.exception.InvalidInputException;
import com.fyp.cm.exception.ResourceNotFoundException;
import com.fyp.cm.model.Device;
import com.fyp.cm.model.House;
import com.fyp.cm.model.HouseParameter;
import com.fyp.cm.model.User;
import com.fyp.cm.repo.DeviceRepo;
import com.fyp.cm.repo.HouseParameterRepo;
import com.fyp.cm.repo.HouseRepo;
import com.fyp.cm.repo.UserRepo;

@Service
public class HouseServiceImpl implements HouseService{
    @Autowired
    private HouseRepo houseRepo;
    @Autowired
    private HouseParameterRepo houseParameterRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private DeviceRepo deviceRepo;
    // Get all houses
    public List<House> getAllHouses() {
        return houseRepo.findAll();
    }

    // Get a house by id
    public House getHouseById(String id) {
        return houseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("House not found with id: " + id));
    }

    // Create a new house
    public House createHouse(HouseDto houseDto) {
        // Validate the house properties
        validateHouse(houseDto);
        House savedHouse = new House();
        savedHouse.setAddress(houseDto.getAddress());
        savedHouse.setState(houseDto.getState());
        House newHouse = houseRepo.save(savedHouse);
        // save house parameter
        HouseParameter houseParameter = new HouseParameter();
        houseParameter.setHouseParameterList(houseDto.getHouseParameters());     
        houseParameter.setHouse(newHouse);
        houseParameterRepo.save(houseParameter);
        newHouse.setHouseParameters(houseDto.getHouseParameters());

         // Set userList
         List<User> users = new ArrayList<>();
         for (User user : houseDto.getUsers()) {
             Optional<User> optionalUser = userRepo.findById(user.getId());
             System.out.println(optionalUser);
             optionalUser.ifPresent(existingUser -> {
                //  existingUser.setEmail(user.getEmail());
                //  existingUser.setUsername(user.getUsername());
                 existingUser.setAdmin(user.isAdmin());
                 existingUser.setActive(true);
                 existingUser.setHouse(newHouse);
                 userRepo.save(existingUser);
                 users.add(existingUser);
             });
         }
         newHouse.setUsers(users);
 
         // Set deviceIdList
         List<Device> devices = new ArrayList<>();
         for (Device device : houseDto.getDevices()) {
             Optional<Device> optionalDevice = deviceRepo.findById(device.getId());
             System.out.println(optionalDevice);
             optionalDevice.ifPresent(existingDevice -> {
                 existingDevice.setState(device.getState());
                 existingDevice.setIsInstalled(device.getIsInstalled());
                 deviceRepo.save(existingDevice);
                 devices.add(existingDevice);
             });
         }
        newHouse.setDevices(devices);

        return houseRepo.save(savedHouse);
    }

    // Update a house by id
    public House updateHouse(String id, HouseDto houseDto) {
        House existingHouse = getHouseById(id);
    
        if (existingHouse != null) {
            existingHouse.setAddress(houseDto.getAddress());
            existingHouse.setState(houseDto.getState());
            
            // Update house parameters
            HouseParameter houseParameter = houseParameterRepo.findByHouse(existingHouse);
            houseParameter.setHouseParameterList(houseDto.getHouseParameters());
            houseParameterRepo.save(houseParameter);
            existingHouse.setHouseParameters(houseDto.getHouseParameters());
    
            // Update user list
            List<User> updatedUsers = new ArrayList<>();
            for (User user : houseDto.getUsers()) {
                Optional<User> optionalUser = userRepo.findById(user.getId());
                optionalUser.ifPresent(existingUser -> {
                    existingUser.setAdmin(user.isAdmin());
                    existingUser.setActive(user.isActive());
                    userRepo.save(existingUser);
                    updatedUsers.add(existingUser);
                });
            }
            existingHouse.setUsers(updatedUsers);
    
            // Update device list
            List<Device> updatedDevices = new ArrayList<>();
            for (Device device : houseDto.getDevices()) {
                Optional<Device> optionalDevice = deviceRepo.findById(device.getId());
                optionalDevice.ifPresent(existingDevice -> {
                    existingDevice.setState(device.getState());
                    existingDevice.setIsInstalled(true);
                    deviceRepo.save(existingDevice);
                    updatedDevices.add(existingDevice);
                });
            }
            existingHouse.setDevices(updatedDevices);
    
            return houseRepo.save(existingHouse);
        } else {
            throw new ResourceNotFoundException("House not found with id: " + id);
        }
    }
    

    // Delete a house by id
    public Boolean deleteHouse(String id) {
        // Find the existing house by id
        House existingHouse = getHouseById(id);
        if (existingHouse != null) {
            // Delete the house from the database
            houseRepo.delete(existingHouse);
            return true;
        } else {
            return false;
        }
    }
    private void validateHouse(HouseDto house) {
        System.out.println(house);
        // Check if the address is null or empty
        if (house.getAddress() == null || house.getAddress().isEmpty()) {
            throw new InvalidInputException("Address cannot be null or empty");
        }
        // Check if the state is null or empty
        if (house.getState() == null || house.getState().isEmpty()) {
            throw new InvalidInputException("State cannot be null or empty");
        }
        // Check if the devices list is null or empty
        if (house.getDevices() == null || house.getDevices().isEmpty()) {
            throw new InvalidInputException("Devices cannot be null or empty");
        }
        // Check if the users list is null or empty
        if (house.getUsers() == null || house.getUsers().isEmpty()) {
            throw new InvalidInputException("Users cannot be null or empty");
        }
    }

    public void changeAdmin(String houseId, String userId) {
        House existingHouse = houseRepo.findById(houseId)
                .orElseThrow(() -> new ResourceNotFoundException("House not found with id: " + houseId));

        List<User> updatedUsers = existingHouse.getUsers().stream()
                .map(user -> {
                    if (user.isAdmin()) {
                        user.setAdmin(false);
                    } else if (user.getId().equals(userId)) {
                        user.setAdmin(true);
                    }
                    return user;
                })
                .collect(Collectors.toList());

        existingHouse.setUsers(updatedUsers);
        houseRepo.save(existingHouse);
    }
}
