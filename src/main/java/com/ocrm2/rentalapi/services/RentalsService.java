package com.ocrm2.rentalapi.services;

import com.ocrm2.rentalapi.dto.RentalsDTO;
import com.ocrm2.rentalapi.dto.RentalsFormDTO;
import com.ocrm2.rentalapi.dto.RentalsListDTO;
import com.ocrm2.rentalapi.models.Rentals;
import com.ocrm2.rentalapi.models.Users;
import com.ocrm2.rentalapi.repository.RentalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class RentalsService {

    @Value("${baseUrl}")
    private String baseUrl;
    @Value("${file.upload-dir}")
    private String uploadDir;

    @Autowired
    RentalsRepository rentalsRepository;
    @Autowired
    UsersService usersService;

    public List<Rentals> getAllRentals() {
        return rentalsRepository.findAll();
    }

    public Rentals getRentalById(final int id){
        return rentalsRepository.findById(id).orElseThrow(()-> new RuntimeException("rental introuvable") );
    }

    public void registerRental(RentalsFormDTO rentalsFormDTO) throws IOException {
        String fileName = saveImgOnSever(rentalsFormDTO.getPicture());

        Users user = usersService.getCurrentUserFromSecurityContext();// on recupere le user qui veux crée le rental

        Rentals rental = createRental(rentalsFormDTO, user, fileName);

        rentalsRepository.save(rental);
    }

    private static Rentals createRental(RentalsFormDTO rentalsFormDTO, Users user, String fileName) {
        Rentals rental = new Rentals();

        rental.setName(rentalsFormDTO.getName());
        rental.setSurface(rentalsFormDTO.getSurface());
        rental.setPrice(rentalsFormDTO.getPrice());
        rental.setDescription(rentalsFormDTO.getDescription());
        rental.setOwnerId(user.getId());
        rental.setPicture(fileName);

        return rental;
    }
    public void updateRental(int id, RentalsFormDTO rentalsFormDTO) {
        Rentals rental = rentalsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rental introuvable"));


        // Vérifie que l'utilisateur est bien le propriétaire
        Users user = usersService.getCurrentUserFromSecurityContext();
        if (user.getId() != rental.getOwnerId()) {
            throw new RuntimeException("Non autorisé à modifier ce rental");
        }

        //Maj du rental
        rental.setName(rentalsFormDTO.getName());
        rental.setSurface(rentalsFormDTO.getSurface());
        rental.setPrice(rentalsFormDTO.getPrice());
        rental.setDescription(rentalsFormDTO.getDescription());

        rentalsRepository.save(rental);
    }

    public RentalsDTO toDTO(Rentals rental) {

        String imageUrl = baseUrl + "/api/images/" + rental.getPicture();

        return new RentalsDTO(
                rental.getId(),
                rental.getName(),
                rental.getSurface(),
                rental.getPrice(),
                imageUrl,
                rental.getDescription(),
                rental.getOwnerId(),
                rental.getCreatedAt(),
                rental.getUpdatedAt()
        );
    }

    public RentalsListDTO toDTOList(List<Rentals> rentalsList) {
        List<RentalsDTO> result = new ArrayList<>();
        for (Rentals rental : rentalsList) {
            RentalsDTO dto = toDTO(rental);
            result.add(dto);
        }

        return new RentalsListDTO(result);
    }

    public String saveImgOnSever(MultipartFile imgFile) throws IOException {
        String fileName = null;

        if (imgFile != null && !imgFile.isEmpty()) {
            Path uploadPath = Paths.get(uploadDir);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }
            fileName = System.currentTimeMillis() + "_" + imgFile.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);

            //les deux ligne ci-dessous font la meme chose mais la premiere est une solution multi-plateforme
            Files.copy(imgFile.getInputStream(), filePath);
            //imgFile.transferTo(new java.io.File(uploadDir, fileName));
        } else {
            throw new RuntimeException("Aucune image reçu");
        }

        return fileName;
    }


}
