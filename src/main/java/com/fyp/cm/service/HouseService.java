package com.fyp.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fyp.cm.dto.HouseDto;
import com.fyp.cm.model.House;
@Service
public interface HouseService {
    List<House> getAllHouses();
    House getHouseById(String id);
    House createHouse(HouseDto houseDto);
    House updateHouse(String id, HouseDto houseDto);
    Boolean deleteHouse(String id);
    void changeAdmin(String houseId, String userId);
}
