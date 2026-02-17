package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findAllByUserIdUser(Long userId);
}
