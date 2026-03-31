package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaceRepository extends JpaRepository<Place, Long> {
    long countByParkingIdParking(Long parkingId);
    List<Place> findByParking_IdParking(Long parkingId);
}