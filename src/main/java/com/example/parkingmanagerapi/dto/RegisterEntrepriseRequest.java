package com.example.parkingmanagerapi.dto;

import lombok.Data;

@Data
public class RegisterEntrepriseRequest {
    // Infos User
    private String name;
    private String surname;
    private String mail;
    private String password;

    // Info Entreprise
    private String nomEntreprise;
}