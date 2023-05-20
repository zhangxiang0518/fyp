package com.fyp.cm.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.Parameter;
@Repository
public interface ParameterRepo extends MongoRepository<Parameter,String>{
    
}
