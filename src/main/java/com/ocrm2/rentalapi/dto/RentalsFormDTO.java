package com.ocrm2.rentalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalsFormDTO {
    private int id;
    private String name;
    private int surface;
    private int price;
    private MultipartFile picture;
    private String description;
    private int ownerId;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}

