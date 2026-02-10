package com.example.parkingmanagerapi;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingManagerApiApplication {

    public static void main(String[] args) {

        //on récupérer les valeurs du .env
        Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
        dotenv.entries().forEach(e -> System.setProperty(e.getKey(), e.getValue()));

        SpringApplication.run(ParkingManagerApiApplication.class, args);
    }

}
