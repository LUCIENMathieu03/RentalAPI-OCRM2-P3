package com.ocrm2.rentalapi.services;

import com.ocrm2.rentalapi.dto.RegisterRequestDTO;
import com.ocrm2.rentalapi.dto.TokenResponseDTO;
import com.ocrm2.rentalapi.dto.UsersDTO;
import com.ocrm2.rentalapi.models.Users;
import com.ocrm2.rentalapi.repository.UsersRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    public Users getUserById(final int id) {
        System.out.println(usersRepository.findById(id));
        return usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));
    }

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
        //usersRepository.flush();
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
        String token = jwtService.generateToken(authentication);

        return new TokenResponseDTO(token);
    }

    public Users getCurrentUserFromSecurityContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {

            throw new AccessDeniedException("User not authenticated");
        }
        String email = authentication.getName();

        return usersRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    public UsersDTO toDTO(Users user) {
        UsersDTO userDTO = new UsersDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setCreatedAt(user.getCreatedAt());
        userDTO.setUpdatedAt(user.getUpdatedAt());

        return userDTO;
    }

}
