package com.fyp.cm.model;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "climate_info")
@Getter
@Setter
@Builder
public class ClimateInfo extends BaseEntity{

    private String climateDataFromSensor;
    @DBRef
    private House house;
}
