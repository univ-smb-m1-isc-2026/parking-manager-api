package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.ParkingRequest;
import com.example.parkingmanagerapi.dto.ParkingDTO;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.entity.Parking;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import com.example.parkingmanagerapi.repository.ParkingRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParkingService {

    @Autowired
    private final ParkingRepository parkingRepository;
    @Autowired
    private final EntrepriseRepository entrepriseRepository;

    public String creerParking(ParkingRequest request) {

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

    public ParkingDTO updateParking(Long parkingId, ParkingRequest request) {

        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new RuntimeException("Parking not found"));

        parking.setName(request.getName());
        parking.setDescription(request.getDescription());
        parking.setLinkMaps(request.getLinkMaps());

        if (request.getEntrepriseId() != null) {
            Entreprise entreprise = entrepriseRepository.findById(request.getEntrepriseId())
                    .orElseThrow(() -> new RuntimeException("Entreprise not found"));
            parking.setEntreprise(entreprise);
        }

        Parking updated = parkingRepository.save(parking);
        return toDto(updated);
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

    public Optional<ParkingDTO> findParking(String id) {
        Long parkingId = Long.parseLong(id);
        return parkingRepository.findById(parkingId)
                .map(this::toDto);
    }


    public List<ParkingDTO> findParkingByEntreprise(String id) {
        Long entrepriseId = Long.parseLong(id);
        return parkingRepository.findAllByEntreprise_IdEntreprise(entrepriseId)
                .stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional
    public void suppParking(String parkingId) {
        Long id = Long.parseLong(parkingId);

        if (!parkingRepository.existsById(id)) {
            throw new RuntimeException("Parking not found");
        }

        parkingRepository.deleteById(id);
    }


}

