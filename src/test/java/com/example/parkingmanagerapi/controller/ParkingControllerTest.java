package com.example.parkingmanagerapi.controller;

import com.example.parkingmanagerapi.dto.ParkingRequest;
import com.example.parkingmanagerapi.service.JwtService;
import com.example.parkingmanagerapi.service.ParkingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ParkingController.class)
class ParkingControllerTest {

    @Autowired
    private MockMvc mockMvc; // Outil pour simuler des appels HTTP sans lancer le serveur

    @MockBean
    private ParkingService parkingService; // Simule le service métier

    @MockBean
    private JwtService jwtService; // Simule le service JWT

    @Autowired
    private ObjectMapper objectMapper; // Utilitaire Jackson pour transformer des objets en JSON

    @Test
    @WithMockUser // Simule un utilisateur par défaut
    void shouldAddParking() throws Exception {
        // Préparation des données
        ParkingRequest request = new ParkingRequest();
        request.setName("Parking Centre");
        request.setDescription("Sous-sol sécurisé");
        request.setLinkMaps("https://maps.google.com");
        request.setEntrepriseId(1L);

        // On définit le comportement du mock : quand on appelle creerParking, il retourne un succès
        Mockito.when(parkingService.creerParking(Mockito.any()))
                .thenReturn("Parking ajouter");

        // Exécution et vérification
        mockMvc.perform(post("/api/parking/addParking")
                        .with(csrf()) //ajoute un jeton CSRF valide à la requête de test
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))) // Conversion de l'objet DTO en String JSON
                .andExpect(status().isOk()); // On vérifie que le contrôleur répond bien 200 (OK)
    }
}