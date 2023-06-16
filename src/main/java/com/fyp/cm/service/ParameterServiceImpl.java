package com.fyp.cm.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fyp.cm.exception.ResourceNotFoundException;
import com.fyp.cm.model.Parameter;
import com.fyp.cm.repo.ParameterRepo;

@Service
public class ParameterServiceImpl implements ParameterService {
    private ParameterRepo parameterRepo;

    @Autowired
    public ParameterServiceImpl(ParameterRepo parameterRepo) {
        this.parameterRepo = parameterRepo;
    }

    @Override
    public List<Parameter> getAllParameters() {
        return parameterRepo.findAll();
    }

    @Override
    public Parameter addParameter(Parameter parameter) {
        validateParameter(parameter);
        return parameterRepo.save(parameter);
    }

    @Override
    public Parameter getParameterById(String id) {
        return parameterRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("parameter not found with id: " + id));
                
    }

    @Override
    public Parameter updateParameter(String id, Parameter parameter) {
        Parameter existingParameter = getParameterById(id);
        existingParameter.setName(parameter.getName());
        existingParameter.setUnit(parameter.getUnit());
        return parameterRepo.save(existingParameter);
    }

    @Override
    public boolean deleteParameter(String id) {
        if (parameterRepo.existsById(id)) {
            parameterRepo.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    private void validateParameter(Parameter parameter) {
        if (parameter.getName() == null || parameter.getName().isEmpty()) {
            throw new IllegalArgumentException("Parameter Name cannot be null or empty");
        }
        if (parameter.getUnit() == null || parameter.getUnit().isEmpty()) {
            throw new IllegalArgumentException("Parameter Unit cannot be null or empty");
        }
    }
    public String[] getAllParameterName() {
        List<Parameter> parameters = parameterRepo.findAll();

        // Create an array to store the parameter names
        String[] parameterNames = new String[parameters.size()];

        // Extract the names from the parameters and populate the array
        for (int i = 0; i < parameters.size(); i++) {
            parameterNames[i] = parameters.get(i).getName();
        }

        return parameterNames;
    }
}
