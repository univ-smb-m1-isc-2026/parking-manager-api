package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.GenerationPlaceRequest;
import com.example.parkingmanagerapi.entity.Place;
import com.example.parkingmanagerapi.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/place")
@RequiredArgsConstructor
public class PlaceController {

    private final PlaceService placeService;

    @PostMapping("/generer")
    public ResponseEntity<List<Place>> genererPlaces(@RequestBody GenerationPlaceRequest request) {
        List<Place> placesCrees = placeService.creerMultiplePlaces(
                request.getParkingId(),
                request.getQuantite()
        );
        return ResponseEntity.ok(placesCrees);
    }
}