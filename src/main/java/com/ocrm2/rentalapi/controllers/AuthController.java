package com.ocrm2.rentalapi.controllers;

import com.ocrm2.rentalapi.dto.RegisterRequestDTO;
import com.ocrm2.rentalapi.dto.TokenResponseDTO;
import com.ocrm2.rentalapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public String index() {
        return "Bienvenue sur l’API Rentals 👋";
    }

    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        try{
            usersService.registerUser(registerRequestDTO);
            TokenResponseDTO token = usersService.authenticateUser(registerRequestDTO.getEmail(), registerRequestDTO.getPassword());

            return ResponseEntity.ok(token);

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
