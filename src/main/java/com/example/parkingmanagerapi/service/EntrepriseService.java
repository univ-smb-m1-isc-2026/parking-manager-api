package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.EntrepriseRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EntrepriseService {
    @Autowired
    private final EntrepriseRepository entrepriseRepository;

    public Entreprise updateEntreprise(Long parkingId, EntrepriseRequest request) {
        Entreprise e = entrepriseRepository.findById(parkingId)
                .orElseThrow(() -> new RuntimeException("Entreprise not found"));

        e.setNom(request.getName());
        return entrepriseRepository.save(e);
    }


    public List<Entreprise> findAllEntreprise() {
        return entrepriseRepository.findAll();
    }

    public Optional<Entreprise> findEntreprise(String id) {
        Long entrepriseId = Long.parseLong(id);
        return entrepriseRepository.findById(entrepriseId);
    }

}