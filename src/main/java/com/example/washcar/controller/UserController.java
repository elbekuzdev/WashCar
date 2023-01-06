package com.example.washcar.controller;

import com.example.washcar.dto.UserDto;
import com.example.washcar.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/signUp")
    public ResponseEntity<?> save(@RequestBody UserDto userDto){
        return userService.save(userDto);
    }
    @PostMapping("/signIn")
    public ResponseEntity<?> login(@RequestParam String login, @RequestParam String password){
        return userService.login(login, password);
    }

}
