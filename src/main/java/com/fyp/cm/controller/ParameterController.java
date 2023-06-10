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
import com.fyp.cm.service.ParameterService;

@RestController
@RequestMapping("/parameter")
public class ParameterController {
    @Autowired
    private ParameterService parameterService;

    @GetMapping
    public ResponseEntity<List<Parameter>> getAllParameters() {
        List<Parameter> parameters = parameterService.getAllParameters();
        return ResponseEntity.ok(parameters); // return 200 OK with the list of parameters
    }

    @PostMapping
    public ResponseEntity<Parameter> addParameter(@RequestBody Parameter parameter) {
        Parameter savedParameter = parameterService.addParameter(parameter);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedParameter);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Parameter> getParameterById(@PathVariable String id) {
        Parameter parameter = parameterService.getParameterById(id);
        if (parameter != null) {
            return ResponseEntity.ok(parameter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Parameter> updateParameter(@PathVariable String id, @RequestBody Parameter parameter) {
        Parameter updatedParameter = parameterService.updateParameter(id, parameter);
        if (updatedParameter != null) {
            return ResponseEntity.ok(updatedParameter);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteParameter(@PathVariable String id) {
        boolean deleted = parameterService.deleteParameter(id);
        if (deleted) {
            return ResponseEntity.ok(id+" deleted successful");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
