package com.fyp.cm.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.ClimateInfo;
@Repository
public interface ClimateInfoRepo extends MongoRepository<ClimateInfo,String>{
    
}
