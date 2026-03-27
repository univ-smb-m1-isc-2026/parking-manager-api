package com.example.parkingmanagerapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "DemandePlacePermanante")
public class DemandePlacePermanante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDemandePlacePermanante;

    private Integer etat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idUser", nullable = false, referencedColumnName = "idUser")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_idEntreprise", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Entreprise entreprise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Place_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Place place;
}
