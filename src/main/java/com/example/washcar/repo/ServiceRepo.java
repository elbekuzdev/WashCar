package com.example.washcar.repo;

import com.example.washcar.entity.Service;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
    List<Service> findByWashCompanyIdAndNameContaining(int washCompanyId, String name, Pageable pageable);
}
