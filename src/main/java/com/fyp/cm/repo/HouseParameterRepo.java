package com.fyp.cm.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.House;
import com.fyp.cm.model.HouseParameter;
@Repository
public interface HouseParameterRepo extends MongoRepository<HouseParameter,String>{
    HouseParameter findByHouse(House house);
}
