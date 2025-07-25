package com.ocrm2.rentalapi.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @GetMapping("/")
    public String index() {
        return "Bienvenue sur l’API Rentals 👋";
    }
}
