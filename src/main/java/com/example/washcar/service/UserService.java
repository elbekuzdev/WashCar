package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.UserDto;
import com.example.washcar.entity.Users;
import com.example.washcar.mapper.UserMapper;
import com.example.washcar.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    public ResponseEntity<?> save(UserDto userDto) {
        Users user = UserMapper.toEntity(userDto);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            if (!userRepo.existsUserByLogin(user.getLogin())) {
                Users save = userRepo.save(user);
                save.setPassword(null);
                return ResponseEntity.ok(new ResponseDto(200, "saved", save));
            } else {
                return ResponseEntity.ok(new ResponseDto(201, "login is used", null));
            }

        } else {
            return ResponseEntity.ok(new ResponseDto(201, "user is invalid", null));
        }
    }

    public ResponseEntity<?> login(String login, String password) {
        Optional<Users> optionalUsers = userRepo.findByLogin(login);
        if (optionalUsers.isPresent()) {
            Users user = optionalUsers.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                user.setPassword(null);
                return ResponseEntity.ok(new ResponseDto(202, "successfully entered", user));

            } else {
                return ResponseEntity.ok(new ResponseDto(202, "username or password incorrect", null));

            }
        } else {
            return ResponseEntity.ok(new ResponseDto(202, "username or password incorrect", null));
        }
    }


}
