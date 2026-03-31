package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.entity.PeriodeDispo;
import com.example.parkingmanagerapi.service.PeriodeDispoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/periodes")
@RequiredArgsConstructor
public class PeriodeDispoController {

    private final PeriodeDispoService periodeDispoService;

    @PostMapping
    public ResponseEntity<PeriodeDispo> create(@RequestBody PeriodeDispo periode) {
        return ResponseEntity.ok(periodeDispoService.addPeriode(periode));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        periodeDispoService.deletePeriode(id);
        return ResponseEntity.ok("Période supprimée avec succès");
    }

    @GetMapping("/all")
    public ResponseEntity<List<PeriodeDispo>> getAllPeriodes() {
        return ResponseEntity.ok(periodeDispoService.getAllPeriodes());
    }

    @GetMapping("/entreprise/{idEntreprise}")
    public ResponseEntity<List<PeriodeDispo>> getByEntrepriseId(@PathVariable Long idEntreprise) {
        return ResponseEntity.ok(periodeDispoService.getPeriodesByEntrepriseId(idEntreprise));
    }
}
