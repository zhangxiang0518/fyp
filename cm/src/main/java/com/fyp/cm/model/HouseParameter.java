package com.fyp.cm.model;

import java.util.Map;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "house_parameter")
@Getter
@Setter
@NoArgsConstructor
public class HouseParameter extends BaseEntity{
    
    private Map<String, Boolean> houseParameterList;
    
    @DBRef
    private House house;
}
