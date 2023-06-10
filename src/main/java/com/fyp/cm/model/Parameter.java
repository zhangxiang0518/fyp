package com.fyp.cm.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "parameter")
@Getter
@Setter
@Builder
public class Parameter extends BaseEntity{
    private String name;
    private String unit;
}
