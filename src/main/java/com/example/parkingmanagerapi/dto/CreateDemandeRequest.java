package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class CreateDemandeRequest {
    private Long userId;
    private Long entrepriseId;
    private Long parkingId;
}
