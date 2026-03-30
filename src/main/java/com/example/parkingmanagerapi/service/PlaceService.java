package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.entity.Place;
import com.example.parkingmanagerapi.entity.Parking;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.PlaceRepository;
import com.example.parkingmanagerapi.repository.ParkingRepository;
import com.example.parkingmanagerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ParkingRepository parkingRepository;
    private final UserRepository userRepository;

    @Transactional
    public List<Place> creerMultiplePlaces(Long parkingId, int nombreDePlaces, int tarifAnnuel, int tarifJournalier) {
        System.out.println("Tentative de création : ParkingID=" + parkingId + ", Quantité=" + nombreDePlaces);

        if (parkingId == null) {
            throw new RuntimeException("L'ID du parking est NULL !");
        }
        Parking parking = parkingRepository.findById(parkingId)
                .orElseThrow(() -> new RuntimeException("Parking non trouvé"));

        long placesExistantes = placeRepository.countByParkingIdParking(parkingId);

        List<Place> nouvellesPlaces = new ArrayList<>();

        for (int i = 1; i <= nombreDePlaces; i++) {
            Place place = new Place();
            place.setParking(parking);
            place.setUser(null);
            place.setEtat(false);
            place.setTarifAnnuel(tarifAnnuel);
            place.setTarifJournalier(tarifJournalier);

            long prochainNumero = placesExistantes + i;
            place.setNumero("Place n°" + prochainNumero);

            nouvellesPlaces.add(place);
        }

        return placeRepository.saveAll(nouvellesPlaces);
    }

    @Transactional
    public Place assignerUserAPlace(Long placeId, Long userId) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new RuntimeException("Place introuvable"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        place.setUser(user);
        place.setEtat(true);

        return placeRepository.save(place);
    }
}