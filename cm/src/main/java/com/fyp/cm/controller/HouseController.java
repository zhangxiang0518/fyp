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

import com.fyp.cm.dto.HouseDto;
import com.fyp.cm.model.House;
import com.fyp.cm.service.HouseService;

// A controller class for the House entity
@RestController
@RequestMapping("/house")
public class HouseController {

    // Autowire the HouseService to handle the business logic
    @Autowired
    private HouseService houseService;
  
    // Get all houses
    @GetMapping
    public ResponseEntity<List<House>> getAllHouses() {
      List<House> houses = houseService.getAllHouses();
      
      return ResponseEntity.ok(houses); // return 200 OK with the list of houses
    }

    // Get a house by id
    @GetMapping("/{id}")
    public ResponseEntity<House> getHouseById(@PathVariable String id) {
      House house = houseService.getHouseById(id);
      return ResponseEntity.ok(house); // return 200 OK with the house
    }
  
    // Create a new house
    @PostMapping
    public ResponseEntity<House> createHouse(@RequestBody HouseDto houseDto) {
      House createdHouse = houseService.createHouse(houseDto);
      return ResponseEntity.status(HttpStatus.CREATED).body(createdHouse); // return 201 CREATED with the created house
    }
  
    // Update a house by id
    @PutMapping("/{id}")
    public ResponseEntity<House> updateHouse(@PathVariable String id, @RequestBody HouseDto houseDto) {
        House updatedHouse = houseService.updateHouse(id, houseDto);
        if (updatedHouse != null) {
            return ResponseEntity.ok(updatedHouse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHouse(@PathVariable String id) {
        boolean deleted = houseService.deleteHouse(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
  }
  
