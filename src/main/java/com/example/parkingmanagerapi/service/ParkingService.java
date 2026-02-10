package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.AddParkingRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.entity.Parking;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import com.example.parkingmanagerapi.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParkingService {

    @Autowired
    private final ParkingRepository parkingRepository;
    @Autowired
    private final EntrepriseRepository entrepriseRepository;

    public String creerParking(AddParkingRequest request) {

        Entreprise entreprise = entrepriseRepository.findById(request.getEntrepriseId())
                .orElseThrow(() -> new RuntimeException("Entreprise not found"));

        Parking parking = new Parking();
        parking.setName(request.getName());
        parking.setDescription(request.getDescription());
        parking.setLinkMaps(request.getLinkMaps());
        parking.setEntreprise(entreprise);

        parkingRepository.save(parking);

        return "Parking ajouter";
    }
}

