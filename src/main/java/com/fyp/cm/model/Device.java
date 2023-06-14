package com.fyp.cm.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "device")
@Builder
@Setter
@Getter
public class Device extends BaseEntity{

    private String serialNum;
    private String type;
    private Boolean isInstalled;
    private String state;
}
