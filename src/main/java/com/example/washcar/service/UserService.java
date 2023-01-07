package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.UserDto;
import com.example.washcar.entity.Users;
import com.example.washcar.mapper.UserMapper;
import com.example.washcar.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
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

    public ResponseEntity<?> changePassword(String login, String oldPassword, String newPassword) {
        Optional<Users> optionalUsers = userRepo.findByLogin(login);
        if (optionalUsers.isPresent()) {
            Users users = optionalUsers.get();
            if (passwordEncoder.matches(oldPassword, users.getPassword())) {
                users.setPassword(passwordEncoder.encode(newPassword));
                userRepo.save(users);
                return ResponseEntity.ok(new ResponseDto(403, "password is successfully changed", null));
            } else {
                return ResponseEntity.ok(new ResponseDto(403, "old password is incorrect", null));
            }
        } else {
            return ResponseEntity.ok(new ResponseDto(403, "user not found", null));
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optionalUsers = userRepo.findByLogin(username);
        if (optionalUsers.isPresent()) return optionalUsers.get();
        else throw new UsernameNotFoundException("user not found");
    }
}
