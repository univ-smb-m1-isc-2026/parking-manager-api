package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class VehiculeRequest {
    private String immatriculation;

    private Long userId;
}
