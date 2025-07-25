package com.ocrm2.rentalapi.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;


@Entity
@Table(name = "USERS")
@Data
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String email;

    String name;

    String password;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    Timestamp createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    Timestamp updatedAt;
}
