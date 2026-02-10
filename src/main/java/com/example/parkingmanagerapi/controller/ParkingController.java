package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.AddParkingRequest;
import com.example.parkingmanagerapi.dto.ParkingDTO;
import com.example.parkingmanagerapi.service.ParkingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/parking")
@RequiredArgsConstructor
public class ParkingController {

    private final ParkingService parkingService;

    @PostMapping("/addParking")
    public String addParking(@RequestBody AddParkingRequest request) {
        return parkingService.creerParking(request);
    }

    @GetMapping("/getParking/{parkingId}")
    public Optional<ParkingDTO> getParking(@PathVariable String parkingId) {
        return parkingService.findParking(parkingId);
    }

    @GetMapping("/getAllParking")
    public List<ParkingDTO> getAllParking() {
        return parkingService.findAllParkings();
    }

}
