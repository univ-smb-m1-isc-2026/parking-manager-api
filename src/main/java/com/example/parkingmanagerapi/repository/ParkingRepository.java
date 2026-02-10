package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Parking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingRepository extends JpaRepository<Parking, Long> {
    List<Parking> findAllByEntreprise_IdEntreprise(Long entrepriseId);
}

