package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public String signUp(@RequestBody Entreprise entreprise) {
        return authService.register(entreprise);
    }

    @PostMapping("/signin")
    public String signIn(@RequestBody Entreprise loginRequest) {
        // Ici on réutilise l'objet Entreprise pour récupérer mail et password
        // (Dans un vrai projet, on utiliserait un DTO spécifique genre "LoginRequest")
        return authService.login(loginRequest.getMail(), loginRequest.getPassword());
    }
}