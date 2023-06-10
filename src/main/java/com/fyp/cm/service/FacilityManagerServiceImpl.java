package com.fyp.cm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.cm.constEnum.LoginResult;
import com.fyp.cm.model.FacilityManager;
import com.fyp.cm.repo.FacilityManagerRepo;

@Service
public class FacilityManagerServiceImpl implements FacilityManagerService {
    @Autowired
    FacilityManagerRepo facilityManagerRepo;

    public FacilityManager register(FacilityManager facilityManager) {
        // Check if username is already taken
        if (facilityManagerRepo.existsByUsername(facilityManager.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Save the facility manager entity
        return facilityManagerRepo.save(facilityManager);
    }

    public LoginResult login(String username, String password) {
        // Find facility manager by username
        FacilityManager facilityManager = facilityManagerRepo.findByUsername(username);

        if (facilityManager == null) {
            return LoginResult.INVALID_USERNAME;
        } else if (!facilityManager.getPassword().equals(password)) {
            return LoginResult.INVALID_PASSWORD;
        }

        return LoginResult.SUCCESS;
    }
}
