package com.fyp.cm.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.House;
@Repository
public interface HouseRepo extends MongoRepository<House,String>{

}
