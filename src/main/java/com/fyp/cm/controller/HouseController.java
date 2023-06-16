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
import com.fyp.cm.response.CustomResponse;
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
  public ResponseEntity<CustomResponse<List<House>>> getAllHouses() {
    List<House> houses = houseService.getAllHouses();

    CustomResponse<List<House>> response = new CustomResponse<>(HttpStatus.OK.value(), "Houses retrieved successfully",
        houses);
    return ResponseEntity.ok(response);
  }

  // Get a house by id
  @GetMapping("/{id}")
  public ResponseEntity<CustomResponse<House>> getHouseById(@PathVariable String id) {
    House house = houseService.getHouseById(id);

    if (house != null) {
      CustomResponse<House> response = new CustomResponse<>(HttpStatus.OK.value(), "House retrieved successfully",
          house);
      return ResponseEntity.ok(response);
    } else {
      CustomResponse<House> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "House not found", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  // Create a new house
  @PostMapping
  public ResponseEntity<CustomResponse<House>> createHouse(@RequestBody HouseDto houseDto) {
    House createdHouse = houseService.createHouse(houseDto);

    CustomResponse<House> response = new CustomResponse<>(HttpStatus.CREATED.value(), "House created successfully",
        createdHouse);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  // Update a house by id
  @PutMapping("/{id}")
  public ResponseEntity<CustomResponse<House>> updateHouse(@PathVariable String id, @RequestBody HouseDto houseDto) {
    House updatedHouse = houseService.updateHouse(id, houseDto);

    if (updatedHouse != null) {
      CustomResponse<House> response = new CustomResponse<>(HttpStatus.OK.value(), "House updated successfully",
          updatedHouse);
      return ResponseEntity.ok(response);
    } else {
      CustomResponse<House> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "House not found", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<CustomResponse<Void>> deleteHouse(@PathVariable String id) {
    boolean deleted = houseService.deleteHouse(id);

    if (deleted) {
      CustomResponse<Void> response = new CustomResponse<>(HttpStatus.OK.value(), "House deleted successfully", null);
      return ResponseEntity.status(HttpStatus.OK).body(response);
    } else {
      CustomResponse<Void> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "House not found", null);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
  }

  @PutMapping("/{houseId}/admin/{userId}")
  public ResponseEntity<CustomResponse<String>> changeAdmin(@PathVariable("houseId") String houseId,
      @PathVariable("userId") String userId) {
    try {
      houseService.changeAdmin(houseId, userId);

      CustomResponse<String> customResponse = new CustomResponse<>(200, "Success", "Admin changed successfully");
      return ResponseEntity.ok(customResponse);
    } catch (Exception e) {
      CustomResponse<String> customResponse = new CustomResponse<>(500, "Error", e.getMessage());
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
    }
  }

}
