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
public class RentalsDTO {
    private int id;
    private String name;
    private int surface;
    private int price;
    private String picture;
    private String description;
    @JsonProperty("owner_id")
    private int ownerId;

    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty(value = "created_at")
    private Timestamp createdAt;
    @JsonFormat(pattern = "yyyy/MM/dd")
    @JsonProperty(value = "updated_at")
    private Timestamp updatedAt;
}
