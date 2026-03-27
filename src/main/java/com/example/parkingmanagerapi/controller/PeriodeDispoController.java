package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.entity.PeriodeDispo;
import com.example.parkingmanagerapi.service.PeriodeDispoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
