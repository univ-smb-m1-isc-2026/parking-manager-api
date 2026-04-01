package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.RegisterEntrepriseRequest;
import com.example.parkingmanagerapi.dto.UserRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.UserRepository;
import com.example.parkingmanagerapi.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register-entreprise")
    public String registerBoss(@RequestBody RegisterEntrepriseRequest request) {
        // 1. Créer l'entreprise (on peut aussi mettre ça dans le service)
        Entreprise ent = new Entreprise();
        ent.setNom(request.getNomEntreprise());
        ent = entrepriseRepository.save(ent);

        // 2. Appeler le service qui va hacher et sauvegarder le patron
        return authService.registerBoss(request, ent);
    }

    @PostMapping("/login-entreprise")
    public String signIn(@RequestBody User loginRequest) {
        return authService.login(loginRequest.getMail(), loginRequest.getPassword());
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/social-login")
    public String socialLogin(@RequestBody UserRequest socialRequest) {
        // 1. Vérifier si l'utilisateur existe déjà
        return userRepository.findByMail(socialRequest.getMail())
            .map(user -> {
                return "Utilisateur existant";
            })
            .orElseGet(() -> {
                // 2. Création du nouvel utilisateur (Salarié sans entreprise au départ)
                User newUser = new User();
                newUser.setMail(socialRequest.getMail());
                newUser.setName(socialRequest.getName());
                newUser.setSurname(socialRequest.getSurname());
                Entreprise entreprise = entrepriseRepository.findById(socialRequest.getEntrepriseId()).get();
                newUser.setEntreprise(entreprise);
                newUser.setStatus(false);
                newUser.setPassword(passwordEncoder.encode("OAUTH_USER_" + Math.random()));

                userRepository.save(newUser);
                return "Nouvel utilisateur créé";
            });
    }
}