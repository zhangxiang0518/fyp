package com.fyp.cm.model;


import org.springframework.data.mongodb.core.mapping.Document;
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
    private boolean isAdmin;
    private boolean isActive;
}