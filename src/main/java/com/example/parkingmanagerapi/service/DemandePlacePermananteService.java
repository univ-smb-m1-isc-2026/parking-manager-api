package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.entity.DemandePlacePermanante;
import com.example.parkingmanagerapi.entity.Place;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.DemandePlacePermananteRepository;
import com.example.parkingmanagerapi.repository.PlaceRepository;
import com.example.parkingmanagerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DemandePlacePermananteService {

    private final DemandePlacePermananteRepository demandeRepository;

    private final UserRepository userRepository;
    private final PlaceRepository placeRepository;

    public DemandePlacePermanante creerDemande(DemandePlacePermanante demande) {
        User user = userRepository.findById(demande.getUser().getIdUser())
                .orElseThrow(() -> new RuntimeException("User introuvable"));

        Place place = placeRepository.findById(demande.getPlace().getIdPlace())
                .orElseThrow(() -> new RuntimeException("Place introuvable"));
        demande.setUser(user);
        demande.setPlace(place);
        demande.setEtat(1);
        return demandeRepository.save(demande);
    }

    public DemandePlacePermanante modifierDemande(Long id, DemandePlacePermanante details) {
        DemandePlacePermanante demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setPlace(details.getPlace());
        return demandeRepository.save(demande);
    }

    public void supprimerDemande(Long id) {
        demandeRepository.deleteById(id);
    }

    @Transactional
    public DemandePlacePermanante accepterDemande(Long id) {
        DemandePlacePermanante demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));

        demande.setEtat(2);

        Place place = demande.getPlace();
        if (place != null) {
            place.setUser(demande.getUser());
            place.setEtat(true);
        }

        return demandeRepository.save(demande);
    }

    public DemandePlacePermanante refuserDemande(Long id) {
        DemandePlacePermanante demande = demandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Demande non trouvée"));
        demande.setEtat(0);
        return demandeRepository.save(demande);
    }

    @Transactional(readOnly = true)
    public List<DemandePlacePermanante> getAllDemandes() {
        return demandeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<DemandePlacePermanante> getDemandesByEntrepriseId(Long idEntreprise) {
        return demandeRepository.findByEntreprise_IdEntreprise(idEntreprise);
    }
}