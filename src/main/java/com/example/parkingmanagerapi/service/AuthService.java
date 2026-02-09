package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.dto.RegisterEntrepriseRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    // --- Register entreprise ---
    public String registerBoss(RegisterEntrepriseRequest request, Entreprise entreprise) {
        User boss = new User();
        boss.setName(request.getName());
        boss.setSurname(request.getSurname());
        boss.setMail(request.getMail());

        boss.setPassword(passwordEncoder.encode(request.getPassword()));

        boss.setStatus(true);
        boss.setEntreprise(entreprise);

        repository.save(boss);
        return "Inscription réussie";
    }

    // --- Login entreprise ---
    public String login(String mail, String password) {
        Optional<User> entrepriseOpt = repository.findByMail(mail);

        if (entrepriseOpt.isPresent()) {
            User user = entrepriseOpt.get();
            // Vérifier si le mot de passe correspond au hash en BDD
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Générer le token
                return jwtService.generateToken(mail);
            }
        }
        throw new RuntimeException("Identifiants invalides");
    }
}