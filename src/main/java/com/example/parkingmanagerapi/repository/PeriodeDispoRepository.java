package com.example.parkingmanagerapi.repository;

import com.example.parkingmanagerapi.entity.PeriodeDispo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PeriodeDispoRepository extends JpaRepository<PeriodeDispo, Long> {

    @Query("SELECT pd FROM PeriodeDispo pd WHERE pd.place.parking.entreprise.idEntreprise = :idEntreprise")
    List<PeriodeDispo> findByEntrepriseId(@Param("idEntreprise") Long idEntreprise);
}