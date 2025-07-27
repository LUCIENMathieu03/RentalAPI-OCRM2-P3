package com.ocrm2.rentalapi.dto;

import lombok.Data;

@Data
public class LoginRequestDTO {
    private String email;
    private String password;
}