package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    //Chercher une entreprise par son nom
    Optional<Entreprise> findByNom(String nom);
}