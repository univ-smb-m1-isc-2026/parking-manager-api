package com.example.parkingmanagerapi.controller;
import com.example.parkingmanagerapi.entity.Hello;
import com.example.parkingmanagerapi.repository.HelloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloRepository helloRepository;

    @GetMapping("/api/coucou")
    public String getCoucou() {
        if (helloRepository.count() == 0) {
            Hello h = new Hello();
            h.setMessage("coucou");
            helloRepository.save(h);
        }

        return helloRepository.findAll().get(0).getMessage();
    }
}