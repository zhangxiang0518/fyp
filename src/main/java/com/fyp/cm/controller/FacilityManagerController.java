package com.fyp.cm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.cm.constEnum.LoginResult;
import com.fyp.cm.model.FacilityManager;
import com.fyp.cm.response.CustomResponse;
import com.fyp.cm.service.FacilityManagerService;

@RestController
@RequestMapping("/fm")
public class FacilityManagerController {
    @Autowired
    private FacilityManagerService facilityManagerService;

@PostMapping("/register")
    public ResponseEntity<CustomResponse<String>> registerFacilityManager(@RequestBody FacilityManager facilityManager) {
        try {
            facilityManagerService.register(facilityManager);
            int statusCode = HttpStatus.OK.value();
            String responseMessage = "Registration successful";
            CustomResponse<String> customResponse = new CustomResponse<>(statusCode, responseMessage, null);
            return ResponseEntity.status(statusCode).body(customResponse);
        } catch (IllegalArgumentException e) {
            int statusCode = HttpStatus.BAD_REQUEST.value();
            String responseMessage = "Invalid authentication";
            CustomResponse<String> customResponse = new CustomResponse<>(statusCode, responseMessage, null);
            return ResponseEntity.status(statusCode).body(customResponse);
        }
    }

      @PostMapping("/login")
    public ResponseEntity<CustomResponse<String>> login(@RequestParam String username, @RequestParam String password) {
        try {
            LoginResult loginResult = facilityManagerService.login(username, password);
            int statusCode;
            String responseMessage;

            switch (loginResult) {
                case SUCCESS:
                    statusCode = HttpStatus.OK.value();
                    responseMessage = "Login successful";
                    break;
                case INVALID_USERNAME:
                    statusCode = HttpStatus.UNAUTHORIZED.value();
                    responseMessage = "Invalid username";
                    break;
                case INVALID_PASSWORD:
                    statusCode = HttpStatus.UNAUTHORIZED.value();
                    responseMessage = "Invalid password";
                    break;
                default:
                    statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
                    responseMessage = "An error occurred during login";
            }
            CustomResponse<String> customResponse = new CustomResponse<>(statusCode, responseMessage, null);
            return ResponseEntity.status(statusCode).body(customResponse);
        } catch (Exception e) {
            int statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
            String responseMessage = "An error occurred during login";
            CustomResponse<String> customResponse = new CustomResponse<>(statusCode, responseMessage, null);
            return ResponseEntity.status(statusCode).body(customResponse);
        }
    }
}
