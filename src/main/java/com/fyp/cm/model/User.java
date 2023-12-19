package com.fyp.cm.model;


import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Document(collection = "user")
@Getter
@Setter
@Builder
//jsoninclude
public class User extends BaseEntity{
    private String username;
    private String password;
    private String email;
    @JsonProperty("isAdmin")
    private boolean isAdmin;
    @JsonProperty("isActive")
    private boolean isActive;
    private String houseId;
}