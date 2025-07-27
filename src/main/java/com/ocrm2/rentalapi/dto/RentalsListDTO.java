package com.ocrm2.rentalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RentalsListDTO {
    List<RentalsDTO> rentals;
}
