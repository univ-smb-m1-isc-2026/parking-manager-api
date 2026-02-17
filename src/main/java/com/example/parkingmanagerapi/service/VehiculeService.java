package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.VehiculeRequest;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.entity.Vehicule;
import com.example.parkingmanagerapi.repository.UserRepository;
import com.example.parkingmanagerapi.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VehiculeService {

    @Autowired
    private final VehiculeRepository vehiculeRepository;
    private final UserRepository userRepository;

    public String creerVehicule(VehiculeRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Vehicule vehicule = new Vehicule();

        vehicule.setImmatriculation(request.getImmatriculation());
        vehicule.setUser(user);

        vehiculeRepository.save(vehicule);

        return "Vehicule ajouter";
    }

    public List<Vehicule> getAllVehicules() {
        return vehiculeRepository.findAll();
    }

    public List<Vehicule> getVehiculeByUserId(String userId) {
        Long id = Long.parseLong(userId);
        return vehiculeRepository.findAllByUserIdUser(id);
    }

    public Vehicule getVehiculeById(String vehiculeId) {
        Long id = Long.parseLong(vehiculeId);
        return vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicule not found"));
    }

    public Vehicule updateVehicule(Long id, VehiculeRequest request) {
        Vehicule v = vehiculeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicule not found"));

        v.setImmatriculation(request.getImmatriculation());

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            v.setUser(user);
        }

        return vehiculeRepository.save(v);

    }

    public void suppVehicule(String vehiculeId){
        Long id = Long.parseLong(vehiculeId);

        if (!vehiculeRepository.existsById(id)) {
            throw new RuntimeException("Vehicule not found");
        }

        vehiculeRepository.deleteById(id);    }
}
