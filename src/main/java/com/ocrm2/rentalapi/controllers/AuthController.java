package com.ocrm2.rentalapi.controllers;

import com.ocrm2.rentalapi.dto.LoginRequestDTO;
import com.ocrm2.rentalapi.dto.RegisterRequestDTO;
import com.ocrm2.rentalapi.dto.RequestResponseDTO;
import com.ocrm2.rentalapi.dto.TokenResponseDTO;
import com.ocrm2.rentalapi.models.Users;
import com.ocrm2.rentalapi.services.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Authentification", description = "Gestion de l'autentification")
@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/")
    public String index() {
        return "Bienvenue sur l’API Rentals 👋";
    }

    @Operation(
            summary = "Creation de compte",
            description = "Permet a un utilisateur de crée un compte"
    )
    @PostMapping("/auth/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        try {
            usersService.registerUser(registerRequestDTO);
            TokenResponseDTO token = usersService.authenticateUser(registerRequestDTO.getEmail(), registerRequestDTO.getPassword());

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Operation(
            summary = "Connexion",
            description = "Permet a un utilisateur de se connecter a son compte"
    )
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            TokenResponseDTO token = usersService.authenticateUser(loginRequestDTO.getEmail(), loginRequestDTO.getPassword()); //si bug dans la verifier si c'est getEmail()

            return ResponseEntity.ok(token);
        } catch (Exception e) {
            RequestResponseDTO login = new RequestResponseDTO("error");
            return ResponseEntity.status(401).body(login);
        }
    }

    @Operation(
            summary = "Inforamtion d'un compte",
            description = "Retourne les information du compte actuelement connecté"
    )
    @SecurityRequirement(name = "bearerAuth")
    @GetMapping("/auth/me")
    public ResponseEntity<?> me() {
        try {
            Users users = usersService.getCurrentUserFromSecurityContext();

            return ResponseEntity.ok(usersService.toDTO(users));
        } catch (Exception e) {
            RequestResponseDTO meError = new RequestResponseDTO("error");

            return ResponseEntity.status(401).body(meError);
        }
    }
}
