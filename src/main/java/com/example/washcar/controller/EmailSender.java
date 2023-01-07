package com.example.washcar.controller;

import com.example.washcar.dto.Message;
import com.example.washcar.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class EmailSender {

    private final EmailSenderService emailSenderService;

    @PreAuthorize("hasAnyRole('ROLE_OWNER')")
    @PostMapping("/sendMessage")
    public ResponseEntity<?> sender(@RequestBody Message message){
        return emailSenderService.sender(message);
    }
}
