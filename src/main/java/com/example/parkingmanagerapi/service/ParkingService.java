package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.AddParkingRequest;
import com.example.parkingmanagerapi.dto.ParkingDTO;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.entity.Parking;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import com.example.parkingmanagerapi.repository.ParkingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public ParkingDTO toDto(Parking parking) {
        ParkingDTO dto = new ParkingDTO();
        dto.setId(parking.getIdParking());
        dto.setName(parking.getName());
        dto.setDescription(parking.getDescription());
        dto.setLinkMaps(parking.getLinkMaps());

        dto.setEntrepriseId(parking.getEntreprise().getIdEntreprise());
        dto.setEntrepriseNom(parking.getEntreprise().getNom());

        return dto;
    }

    public List<ParkingDTO> findAllParkings() {
        return parkingRepository.findAll()
                .stream()
                .map(this::toDto)
                .toList();
    }
}

