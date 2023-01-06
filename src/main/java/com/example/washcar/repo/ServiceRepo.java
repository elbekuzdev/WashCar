package com.example.washcar.repo;

import com.example.washcar.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepo extends JpaRepository<Service, Integer> {
}
