package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EntrepriseRepository extends JpaRepository<User, Long> {
    Optional<User> findByMail(String mail);
}