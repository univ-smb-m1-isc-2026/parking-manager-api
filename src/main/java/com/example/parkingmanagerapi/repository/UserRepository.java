package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Pour retrouver tous les membres d'une entreprise (Utile pour le patron)
    List<User> findByEntreprise(Entreprise entreprise);
    // Pour vérifier si un mail existe déjà lors du sign-in
    boolean existsByMail(String mail);
    Optional<User> findByMail(String mail);
    List<User> findByEntrepriseIdEntreprise(Long idEntreprise);
}