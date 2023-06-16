package com.fyp.cm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fyp.cm.model.Parameter;
import com.fyp.cm.response.CustomResponse;
import com.fyp.cm.service.ParameterService;

@RestController
@RequestMapping("/parameter")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public ResponseEntity<CustomResponse<List<Parameter>>> getAllParameters() {
        List<Parameter> parameters = parameterService.getAllParameters();
        CustomResponse<List<Parameter>> response = new CustomResponse<>(HttpStatus.OK.value(), "Success", parameters);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/names")
    public ResponseEntity<CustomResponse<String[]>> getAllParameterNames() {
        String[] parameterNames = parameterService.getAllParameterName();
        CustomResponse<String[]> response = new CustomResponse<>(HttpStatus.OK.value(), "Success", parameterNames);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CustomResponse<Parameter>> addParameter(@RequestBody Parameter parameter) {
        Parameter savedParameter = parameterService.addParameter(parameter);
        CustomResponse<Parameter> response = new CustomResponse<>(HttpStatus.CREATED.value(), "Parameter added",
                savedParameter);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Parameter>> getParameterById(@PathVariable String id) {
        Parameter parameter = parameterService.getParameterById(id);
        if (parameter != null) {
            CustomResponse<Parameter> response = new CustomResponse<>(HttpStatus.OK.value(), "Success", parameter);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<Parameter> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(),
                    "Parameter not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Parameter>> updateParameter(@PathVariable String id,
            @RequestBody Parameter parameter) {
        Parameter updatedParameter = parameterService.updateParameter(id, parameter);
        if (updatedParameter != null) {
            CustomResponse<Parameter> response = new CustomResponse<>(HttpStatus.OK.value(), "Parameter updated",
                    updatedParameter);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<Parameter> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(),
                    "Parameter not found", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<String>> deleteParameter(@PathVariable String id) {
        boolean deleted = parameterService.deleteParameter(id);
        if (deleted) {
            CustomResponse<String> response = new CustomResponse<>(HttpStatus.OK.value(), "Parameter deleted", id);
            return ResponseEntity.ok(response);
        } else {
            CustomResponse<String> response = new CustomResponse<>(HttpStatus.NOT_FOUND.value(), "Parameter not found",
                    null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }
}
