package com.example.washcar.repo;

import com.example.washcar.entity.Washer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WasherRepo extends JpaRepository<Washer, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
}
