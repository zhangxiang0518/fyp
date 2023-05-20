package com.fyp.cm.dto;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fyp.cm.model.Device;
import com.fyp.cm.model.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class HouseDto {   
    private String address;
    private String state;
    private List<User> userList;
    private List<Device> deviceList;
    private Map<String, Boolean> houseParameters;
    @JsonProperty("userList")
    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @JsonProperty("deviceList")
    public void setDeviceList(List<Device> deviceList) {
        this.deviceList = deviceList;
    }
}