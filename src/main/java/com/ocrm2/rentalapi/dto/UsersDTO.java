package com.ocrm2.rentalapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsersDTO {
    private int id;
    private String name;
    private String email;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty(value = "created_at")
    private Timestamp createdAt;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty(value = "updated_at")
    private Timestamp updatedAt;
}