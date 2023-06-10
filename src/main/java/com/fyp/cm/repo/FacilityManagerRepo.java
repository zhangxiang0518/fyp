package com.fyp.cm.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.FacilityManager;
@Repository
public interface FacilityManagerRepo extends MongoRepository<FacilityManager,String>{
    FacilityManager findByUsername(String username);
    boolean existsByUsername(String username);
}
