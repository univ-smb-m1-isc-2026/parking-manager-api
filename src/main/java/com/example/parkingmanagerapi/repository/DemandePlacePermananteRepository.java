package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.DemandePlacePermanante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DemandePlacePermananteRepository extends JpaRepository<DemandePlacePermanante, Long> {

    List<DemandePlacePermanante> findByEntreprise_IdEntreprise(Long idEntreprise);
}