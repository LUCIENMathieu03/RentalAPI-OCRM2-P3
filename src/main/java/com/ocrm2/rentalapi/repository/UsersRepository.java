package com.ocrm2.rentalapi.repository;

import com.ocrm2.rentalapi.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users,Integer> {
    Optional<Users> findByEmail(String login);
    boolean existsByEmail(String email);

}
