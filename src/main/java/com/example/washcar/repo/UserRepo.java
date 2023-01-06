package com.example.washcar.repo;

import com.example.washcar.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer> {
    boolean existsUserByLogin(String login);
    Optional<Users> findByLogin(String login);
}
