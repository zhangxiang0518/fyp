package com.fyp.cm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.data.mongodb.core.mapping.Document;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "house")
@Setter
@Getter
@NoArgsConstructor
public class House extends BaseEntity{
    private String address;
    private String state;

    // @DBRef(lazy = false)
    // @NotEmpty(message = "Device list must not be empty")
    private List<Device> devices = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private Map<String, Boolean> houseParameters;
}