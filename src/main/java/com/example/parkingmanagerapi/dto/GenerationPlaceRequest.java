package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class GenerationPlaceRequest {
    private Long parkingId;
    private int quantite;
}