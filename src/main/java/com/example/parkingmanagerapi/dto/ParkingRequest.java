package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class ParkingRequest {
    private String name;
    private String description;
    private String linkMaps;
    private Long entrepriseId;
}
