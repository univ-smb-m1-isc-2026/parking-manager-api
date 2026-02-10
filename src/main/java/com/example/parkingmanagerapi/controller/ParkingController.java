package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.AddParkingRequest;
import com.example.parkingmanagerapi.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/addParking")
    public String registerBoss(@RequestBody AddParkingRequest request) {
        return parkingService.creerParking(request);
    }

}
