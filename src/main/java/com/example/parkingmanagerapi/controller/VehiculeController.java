package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.ParkingDTO;
import com.example.parkingmanagerapi.dto.ParkingRequest;
import com.example.parkingmanagerapi.dto.VehiculeRequest;
import com.example.parkingmanagerapi.entity.Vehicule;
import com.example.parkingmanagerapi.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicule")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculeService vehiculeService;

    @PostMapping("/addVehicule")
    public String addVehicule(@RequestBody VehiculeRequest request) {
        return vehiculeService.creerVehicule(request);
    }

    @GetMapping("/getAllVehicule")
    public List<Vehicule> getAllVehicule() {
        return vehiculeService.getAllVehicules();
    }

    @GetMapping("/getVehiculeById/{vehiculeId}")
    public Vehicule getVehiculeById(@PathVariable String vehiculeId) {
        return vehiculeService.getVehiculeById(vehiculeId);
    }

    @GetMapping("/getVehiculeByUserId/{userId}")
    public List<Vehicule> getVehiculeByUserId(@PathVariable String userId) {
        return vehiculeService.getVehiculeByUserId(userId);
    }

    @PutMapping("/editVehicule/{id}")
    public Vehicule editVehicule(
            @PathVariable Long id,
            @RequestBody VehiculeRequest request
    ) {
        return vehiculeService.updateVehicule(id, request);
    }

    @DeleteMapping("/deleteVehicule/{vehiculeId}")
    public void deleteVehicule(@PathVariable String vehiculeId) {
        vehiculeService.suppVehicule(vehiculeId);
    }

}
