package com.example.washcar.repo;

import com.example.washcar.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepo extends JpaRepository<RefreshToken, Integer> {
    Optional<RefreshToken> findByRefreshToken(String refreshToken);
}
