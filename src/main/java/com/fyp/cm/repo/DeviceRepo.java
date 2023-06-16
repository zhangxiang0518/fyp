package com.fyp.cm.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.fyp.cm.model.Device;
@Repository
public interface DeviceRepo extends MongoRepository<Device,String>{
     List<Device> findAllByIsInstalled(boolean isInstalled);
}
