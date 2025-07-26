package com.ocrm2.rentalapi.controllers;

import com.ocrm2.rentalapi.dto.UsersDTO;
import com.ocrm2.rentalapi.models.Users;
import com.ocrm2.rentalapi.repository.UsersRepository;
import com.ocrm2.rentalapi.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UsersController {

    @Autowired
    UsersRepository userRepository;

    @Autowired
    UsersService usersService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") final int id) {
        try {
            Users user = usersService.getUserById(id);
            UsersDTO response = usersService.toDTO(user);

            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }
}
