package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.RegisterEntrepriseRequest;
import com.example.parkingmanagerapi.dto.UserRequest;
import com.example.parkingmanagerapi.entity.Entreprise;
import com.example.parkingmanagerapi.repository.EntrepriseRepository;
import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.UserRepository;
import com.example.parkingmanagerapi.service.AuthService;
import com.example.parkingmanagerapi.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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

    // Crée une petite classe interne pour la réponse ou utilise une Map
    @PostMapping("/social-login")
    public ResponseEntity<?> socialLogin(@RequestBody UserRequest socialRequest) {
        User user = userRepository.findByMail(socialRequest.getMail())
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setMail(socialRequest.getMail());
                    newUser.setName(socialRequest.getName());
                    newUser.setSurname(socialRequest.getSurname());
                    newUser.setStatus(false);
                    newUser.setPassword(passwordEncoder.encode("OAUTH_" + Math.random()));
                    if (socialRequest.getEntrepriseId() != null) {
                        entrepriseRepository.findById(socialRequest.getEntrepriseId()).ifPresent(newUser::setEntreprise);
                    }
                    return userRepository.save(newUser);
                });

        String token = jwtService.generateToken(user.getMail(), 0L, user.getName(), "false");

        // On renvoie un objet JSON au lieu d'un simple String
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("idUser", user.getIdUser()); // L'ID numérique que Postman utilise

        return ResponseEntity.ok(response);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserByMail(@RequestParam("mail") String mail) {
        java.util.Optional<User> userOpt = userRepository.findByMail(mail);

        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Utilisateur non trouvé en base de données.");
        }

        User user = userOpt.get();
        Map<String, Object> response = new HashMap<>();
        response.put("idUser", user.getIdUser());
        response.put("name", user.getName());
        response.put("surname", user.getSurname());
        response.put("mail", user.getMail());

        if (user.getEntreprise() != null) {
            response.put("entrepriseId", user.getEntreprise().getIdEntreprise());
        }

        // 4. On renvoie la Map dans une réponse OK
        return ResponseEntity.ok(response);
    }
}