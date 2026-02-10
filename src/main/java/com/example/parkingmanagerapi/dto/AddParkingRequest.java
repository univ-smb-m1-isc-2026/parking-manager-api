package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class AddParkingRequest {
    private String name;
    private String description;
    private String linkMaps;
    private Long entrepriseId;
}
