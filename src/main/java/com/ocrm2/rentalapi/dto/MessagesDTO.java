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
public class MessagesDTO {
    @JsonProperty("rental_id")
    private int rentalId;
    @JsonProperty("user_id")
    private int userId;
    private String message;

}
