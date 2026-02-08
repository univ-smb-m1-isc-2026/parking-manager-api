package com.example.parkingmanagerapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive la protection CSRF (inutile pour les API REST stateless)
                .authorizeHttpRequests(auth -> auth
                        // On autorise tout le monde à accéder aux routes d'authentification
                        .requestMatchers("/api/auth/**").permitAll()
                        // Tout le reste nécessite d'être connecté
                        .anyRequest().authenticated()
                );

        return http.build();
    }

    // Ce Bean sert à crypter les mots de passe
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}