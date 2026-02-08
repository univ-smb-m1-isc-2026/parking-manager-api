package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private EntrepriseRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // --- SIGN UP ---
    public String register(Entreprise entreprise) {
        // 1. Encoder le mot de passe
        entreprise.setPassword(passwordEncoder.encode(entreprise.getPassword()));
        // 2. Sauvegarder
        repository.save(entreprise);
        return "Entreprise enregistrée avec succès !";
    }

    // --- SIGN IN ---
    public String login(String mail, String password) {
        Optional<Entreprise> entrepriseOpt = repository.findByMail(mail);

        if (entrepriseOpt.isPresent()) {
            Entreprise entreprise = entrepriseOpt.get();
            // Vérifier si le mot de passe correspond au hash en BDD
            if (passwordEncoder.matches(password, entreprise.getPassword())) {
                // Générer le token
                return jwtService.generateToken(mail);
            }
        }
        throw new RuntimeException("Identifiants invalides");
    }
}