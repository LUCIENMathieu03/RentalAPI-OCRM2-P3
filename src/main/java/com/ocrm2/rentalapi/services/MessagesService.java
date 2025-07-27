package com.ocrm2.rentalapi.services;

import com.ocrm2.rentalapi.dto.MessagesDTO;
import com.ocrm2.rentalapi.models.Messages;
import com.ocrm2.rentalapi.repository.MessagesRepository;
import com.ocrm2.rentalapi.repository.RentalsRepository;
import com.ocrm2.rentalapi.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessagesService {

    @Autowired
    MessagesRepository messagesRepository;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    RentalsRepository rentalsRepository;

    public void saveMessage(MessagesDTO messagesDTO){
        if (messagesDTO.getMessage() == null || messagesDTO.getMessage().isEmpty()) {
            throw new RuntimeException("Le message ne peut pas être vide");
        }


        if (!usersRepository.existsById(messagesDTO.getUserId())) {
            throw new RuntimeException("User Id introuvable");
        }

        if (!rentalsRepository.existsById(messagesDTO.getRentalId())) {
            throw new RuntimeException("Rental Id introuvable.");
        }

        Messages message = new Messages();
        message.setMessage(messagesDTO.getMessage());
        message.setUserId(messagesDTO.getUserId());
        message.setRentalId(messagesDTO.getRentalId());

        messagesRepository.save(message);
    }

}

