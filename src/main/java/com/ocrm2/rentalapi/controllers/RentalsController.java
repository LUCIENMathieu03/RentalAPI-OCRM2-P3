package com.ocrm2.rentalapi.controllers;

import com.ocrm2.rentalapi.dto.RentalsDTO;
import com.ocrm2.rentalapi.dto.RentalsFormDTO;
import com.ocrm2.rentalapi.dto.RentalsListDTO;
import com.ocrm2.rentalapi.dto.RequestResponseDTO;
import com.ocrm2.rentalapi.models.Rentals;
import com.ocrm2.rentalapi.services.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RentalsController {

    @Autowired
    RentalsService rentalsService;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @GetMapping("/rentals")
    public ResponseEntity<?> getAllRentals(){
        try{
            List<Rentals> rentals = rentalsService.getAllRentals();
            RentalsListDTO response = rentalsService.toDTOList(rentals);

            return  ResponseEntity.ok(response);
        }catch (Exception e){
            return ResponseEntity.status(404).body("error");
        }
    }

    @PostMapping("/rentals")
    public ResponseEntity<?> addRental(@ModelAttribute RentalsFormDTO rentalsFormDTO) throws IOException {
        rentalsService.registerRental(rentalsFormDTO);

        RequestResponseDTO response = new RequestResponseDTO("Rental created !");
        return ResponseEntity.ok(response);
    }

    @PutMapping ("/rentals/{id}")
    public ResponseEntity<?> updateRental(@ModelAttribute RentalsFormDTO rentalsFormDTO, @PathVariable("id") final int id) {
        rentalsService.updateRental(id, rentalsFormDTO);

        RequestResponseDTO response = new RequestResponseDTO("Rental updated !");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rentals/{id}")
    public ResponseEntity<?> getRental(@PathVariable("id") final int id) {
        Rentals rental = rentalsService.getRentalById(id);
        RentalsDTO response = rentalsService.toDTO(rental);
        return ResponseEntity.ok(response);
    }


    @GetMapping("/images/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
            //Création d'une ressource compréhensible et utilisable par java
            Path filePath = Paths.get(uploadDir).resolve(filename);
            Resource resource = new UrlResource(filePath.toUri());
            System.out.println(filePath);
            if (!resource.exists() || !resource.isReadable()) {
                return ResponseEntity.notFound().build();
            }

            // Détection automatique du type MIME
            String contentType = Files.probeContentType(filePath);
            MediaType mediaType = MediaType.parseMediaType(
                    contentType != null ? contentType : "application/octet-stream"
            );

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .contentType(mediaType)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
