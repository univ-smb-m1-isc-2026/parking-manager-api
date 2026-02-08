package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Entreprise;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {
    Optional<Entreprise> findByMail(String mail);
}