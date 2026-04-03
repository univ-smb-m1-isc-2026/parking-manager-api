package com.example.parkingmanagerapi.service;

import com.example.parkingmanagerapi.entity.User;
import com.example.parkingmanagerapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Récupérer tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Récupérer un utilisateur par son ID
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    // Récupérer les utilisateurs d'une entreprise
    public List<User> getUsersByEntreprise(Long idEntreprise) {
        return userRepository.findByEntrepriseIdEntreprise(idEntreprise);
    }

    // Mettre à jour un utilisateur
    public User updateUser(Long id, User userDetails) {
        User user = getUserById(id);
        user.setName(userDetails.getName());
        user.setSurname(userDetails.getSurname());
        user.setMail(userDetails.getMail());
        user.setEntreprise(userDetails.getEntreprise());
        // On ne met à jour le password que s'il est fourni (et on le hache !)
        if (userDetails.getPassword() != null && !userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
