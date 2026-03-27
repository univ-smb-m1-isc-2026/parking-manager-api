package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.entity.DemandePlacePermanante;
import com.example.parkingmanagerapi.service.DemandePlacePermananteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/demandePermanante")
@RequiredArgsConstructor
public class DemandePlacePermananteController {

    private final DemandePlacePermananteService demandeService;

    @PostMapping
    public ResponseEntity<DemandePlacePermanante> creer(@RequestBody DemandePlacePermanante demande) {
        return ResponseEntity.ok(demandeService.creerDemande(demande));
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
}