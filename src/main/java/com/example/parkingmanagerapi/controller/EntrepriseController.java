package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.EntrepriseRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.service.EntrepriseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/entreprise")
@RequiredArgsConstructor
public class EntrepriseController {
    private final EntrepriseService entrepriseService;

    @PutMapping("/editEntreprise/{id}")
    public Entreprise editEntreprise(
            @PathVariable Long id,
            @RequestBody EntrepriseRequest request
    ) {
        return entrepriseService.updateEntreprise(id, request);
    }


    @GetMapping("/getEntrepriseById/{entrepriseId}")
    public Optional<Entreprise> getEntrepriseById(@PathVariable String entrepriseId) {
        return entrepriseService.findEntreprise(entrepriseId);
    }

    @GetMapping("/getAllEntreprise")
    public List<Entreprise> getAllEntreprise() {
        return entrepriseService.findAllEntreprise();
    }

}
