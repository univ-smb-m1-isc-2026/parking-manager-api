package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Hello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HelloRepository extends JpaRepository<Hello, Long> {
}