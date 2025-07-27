package com.ocrm2.rentalapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class RegisterRequestDTO {
    String email;
    String name;
    String password;
}

