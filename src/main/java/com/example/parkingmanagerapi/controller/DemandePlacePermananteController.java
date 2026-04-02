package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.CreateDemandeRequest;
import com.example.parkingmanagerapi.entity.DemandePlacePermanante;
import com.example.parkingmanagerapi.service.DemandePlacePermananteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/demandePermanante")
@RequiredArgsConstructor
public class DemandePlacePermananteController {

    private final DemandePlacePermananteService demandeService;

    @PostMapping
    public ResponseEntity<DemandePlacePermanante> creer(@RequestBody CreateDemandeRequest request) {
        return ResponseEntity.ok(demandeService.creerDemande(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DemandePlacePermanante> modifier(@PathVariable Long id, @RequestBody DemandePlacePermanante demande) {
        return ResponseEntity.ok(demandeService.modifierDemande(id, demande));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        demandeService.supprimerDemande(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/accepter")
    public ResponseEntity<DemandePlacePermanante> accepter(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.accepterDemande(id));
    }

    @PatchMapping("/{id}/refuser")
    public ResponseEntity<DemandePlacePermanante> refuser(@PathVariable Long id) {
        return ResponseEntity.ok(demandeService.refuserDemande(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<DemandePlacePermanante>> getAllDemandes() {
        return ResponseEntity.ok(demandeService.getAllDemandes());
    }

    @GetMapping("/entreprise/{idEntreprise}")
    public ResponseEntity<List<DemandePlacePermanante>> getByEntrepriseId(@PathVariable Long idEntreprise) {
        return ResponseEntity.ok(demandeService.getDemandesByEntrepriseId(idEntreprise));
    }

    @GetMapping("/user/{idUser}")
    public ResponseEntity<List<DemandePlacePermanante>> getByUserId(@PathVariable Long idUser) {
        return ResponseEntity.ok(demandeService.getDemandesByUserId(idUser));
    }
}