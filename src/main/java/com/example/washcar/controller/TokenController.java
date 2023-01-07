package com.example.washcar.controller;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TokenController {
    private final TokenService tokenService;

    @PostMapping("/accessToken")
    public ResponseDto getAccessToken(@RequestParam String login, @RequestParam String password){
        return tokenService.jwtToken(login,password);
    }

    @PostMapping("/refreshToken")
    public ResponseDto getRefreshToken(@RequestParam String refreshToken){
        return tokenService.refreshToken(refreshToken);
    }
}
