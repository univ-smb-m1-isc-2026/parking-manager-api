package com.example.parkingmanagerapi.controller;
import com.example.parkingmanagerapi.entity.Hello;
import com.example.parkingmanagerapi.repository.HelloRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.slf4j.LoggerFactory.getLogger;

@RestController
@CrossOrigin(
        originPatterns = "*",
        allowCredentials = "true"
)
public class HelloController {



    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ChuckFactsService chuckFactsService;

    public ChuckFactsController(ChuckFactsService chuckFactsService) {
        this.chuckFactsService = chuckFactsService;
    }


    @GetMapping(value = "/api/chuck-facts")
    public List<String> facts() {
        logger.info("Serving Facts");
        return chuckFactsService.facts()
                .stream()
                .map(p -> p.getName())
                .collect(toList());
    }

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