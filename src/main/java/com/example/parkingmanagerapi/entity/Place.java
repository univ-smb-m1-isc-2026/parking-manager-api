package com.example.parkingmanagerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Place")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlace;

    private String numero;

    private boolean etat;
    private Integer tarifAnnuel;
    private Integer tarifJournalier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", nullable = true)
    private User user;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Parking_id", nullable = false)
    private Parking parking;

    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<PeriodeDispo> periodes;
    @JsonIgnore
    @OneToMany(mappedBy = "place", cascade = CascadeType.REMOVE)
    private List<DemandePlacePermanante> demandes;
}
