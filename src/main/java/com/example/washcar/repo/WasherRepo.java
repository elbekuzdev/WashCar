package com.example.washcar.repo;

import com.example.washcar.entity.Washer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WasherRepo extends JpaRepository<Washer, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);
    List<Washer> findByWashCompanyIdAndNameContaining(int washCompanyId, String name, Pageable pageable);
}
