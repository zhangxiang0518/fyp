package com.fyp.cm.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "facility_manager")
@Getter
@Setter
@Builder
public class FacilityManager extends BaseEntity{
    private String username;
    private String password;
}
