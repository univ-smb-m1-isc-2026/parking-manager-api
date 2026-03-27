package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.DemandePlacePermanante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandePlacePermananteRepository extends JpaRepository<DemandePlacePermanante, Long> {
}