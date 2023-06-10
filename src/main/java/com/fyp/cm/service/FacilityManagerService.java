package com.fyp.cm.service;

import org.springframework.stereotype.Service;

import com.fyp.cm.constEnum.LoginResult;
import com.fyp.cm.model.FacilityManager;

@Service
public interface FacilityManagerService {
    FacilityManager register(FacilityManager facilityManager);
    LoginResult login(String username, String password);
}
