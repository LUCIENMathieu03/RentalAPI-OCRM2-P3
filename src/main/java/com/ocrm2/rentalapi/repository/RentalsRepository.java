package com.ocrm2.rentalapi.repository;


import com.ocrm2.rentalapi.models.Rentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepository extends JpaRepository<Rentals,Integer> {
}
