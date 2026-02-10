package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class ParkingDTO {
    private Long id;
    private String name;
    private String description;
    private String linkMaps;

    private Long entrepriseId;
    private String entrepriseNom;
}

