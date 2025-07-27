package com.ocrm2.rentalapi.controllers;

import com.ocrm2.rentalapi.dto.MessagesDTO;
import com.ocrm2.rentalapi.dto.RequestResponseDTO;
import com.ocrm2.rentalapi.services.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MessagesController {
    @Autowired
    MessagesService messagesService;

    @PostMapping("/messages/")
    public ResponseEntity<?> postMessage(@RequestBody MessagesDTO messagesDTO){
        try{
            messagesService.saveMessage(messagesDTO);
            RequestResponseDTO response = new RequestResponseDTO("Message send with success");

            return ResponseEntity.ok(response);
        } catch (RuntimeException e){
            return ResponseEntity.status(401).body("error");
        }

    }
}
