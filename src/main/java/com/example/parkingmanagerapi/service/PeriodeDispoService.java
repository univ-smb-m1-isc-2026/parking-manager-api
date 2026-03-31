package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.entity.PeriodeDispo;
import com.example.parkingmanagerapi.entity.Place;
import com.example.parkingmanagerapi.repository.PeriodeDispoRepository;
import com.example.parkingmanagerapi.repository.PlaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PeriodeDispoService {

    private final PeriodeDispoRepository periodeDispoRepository;
    private final PlaceRepository placeRepository;

    @Transactional
    public PeriodeDispo addPeriode(PeriodeDispo periode) {
        Place place = placeRepository.findById(periode.getPlace().getIdPlace())
                .orElseThrow(() -> new RuntimeException("Place non trouvée"));

        periode.setPlace(place);
        return periodeDispoRepository.save(periode);
    }

    @Transactional
    public void deletePeriode(Long id) {
        if (!periodeDispoRepository.existsById(id)) {
            throw new RuntimeException("Cette période n'existe pas");
        }
        periodeDispoRepository.deleteById(id);
    }

    @Transactional
    public List<PeriodeDispo> getAllPeriodes() {
        return periodeDispoRepository.findAll();
    }

    @Transactional
    public List<PeriodeDispo> getPeriodesByEntrepriseId(Long idEntreprise) {
        return periodeDispoRepository.findByEntrepriseId(idEntreprise);
    }
}
