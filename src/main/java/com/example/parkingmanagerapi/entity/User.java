package com.example.parkingmanagerapi.entity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "entreprise")
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mail;
    private String name_enterprise;
    private String name;
    private String surname;
    private String password;
}
