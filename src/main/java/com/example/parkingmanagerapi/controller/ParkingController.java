package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.ParkingRequest;
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
    public String addParking(@RequestBody ParkingRequest request) {
        return parkingService.creerParking(request);
    }

    @PutMapping("/{id}")
    public ParkingDTO editParking(
            @PathVariable Long id,
            @RequestBody ParkingRequest request
    ) {
        return parkingService.updateParking(id, request);
    }


    @GetMapping("/getParkingById/{parkingId}")
    public Optional<ParkingDTO> getParkingById(@PathVariable String parkingId) {
        return parkingService.findParking(parkingId);
    }

    @GetMapping("/getParkingByEntreprise/{entrepriseId}")
    public List<ParkingDTO> getParkingByEntreprise(@PathVariable String entrepriseId) {
        return parkingService.findParkingByEntreprise(entrepriseId);
    }

    @GetMapping("/getAllParking")
    public List<ParkingDTO> getAllParking() {
        return parkingService.findAllParkings();
    }

    @GetMapping("/deleteParking/{parkingId}")
    public void deleteParking(@PathVariable String parkingId) {
        parkingService.suppParking(parkingId);
    }

}
