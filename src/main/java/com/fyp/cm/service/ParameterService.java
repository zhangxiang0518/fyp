package com.fyp.cm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fyp.cm.model.Parameter;

@Service
public interface ParameterService {
    List<Parameter> getAllParameters();

    Parameter addParameter(Parameter parameter);

    Parameter getParameterById(String id);

    Parameter updateParameter(String id, Parameter parameter);

    boolean deleteParameter(String id);

    String[] getAllParameterName();
}
