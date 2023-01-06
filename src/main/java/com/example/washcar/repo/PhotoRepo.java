package com.example.washcar.repo;

import com.example.washcar.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepo extends JpaRepository<Photo, Integer> {
}
