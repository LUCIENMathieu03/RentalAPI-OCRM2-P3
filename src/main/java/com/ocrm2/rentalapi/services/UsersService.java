package com.ocrm2.rentalapi.services;

import com.ocrm2.rentalapi.dto.RegisterRequestDTO;
import com.ocrm2.rentalapi.dto.TokenResponseDTO;
import com.ocrm2.rentalapi.models.Users;
import com.ocrm2.rentalapi.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Data
@Service
public class UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    /**
     * Save a user in DB if he doesn't exist alredy
     *
     * @param registerRequestDTO information of the user to save
     */
    public void registerUser(RegisterRequestDTO registerRequestDTO) throws RuntimeException {
        if (usersRepository.existsByEmail(registerRequestDTO.getEmail())) {
            throw new RuntimeException("Email already used");
        }

        Users user = new Users();
        user.setName(registerRequestDTO.getName());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequestDTO.getPassword()));

        usersRepository.save(user); //les champs created_at et updated_at sont "sett" lors l'ajout du user en BDD grace à Hibernate par le biais des annotations: @CreationTimestamp et @UpdateTimestamp
        usersRepository.flush();
    }

    /**
     * Authenticate a user in AuthenticationManager and return a valid token
     *
     * @param login
     * @param password
     * @return object with the created token
     */
    public TokenResponseDTO authenticateUser(String login, String password) {

        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(login, password);
        Authentication authentication = authenticationManager.authenticate(authRequest);
        String jwt = jwtService.generateToken(authentication);

        String token = jwtService.generateToken(authentication);

        return new TokenResponseDTO(token);
    }

}
