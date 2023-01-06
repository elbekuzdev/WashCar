package com.example.washcar.service;

import com.example.washcar.dto.Message;
import com.example.washcar.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final JavaMailSender emailSender;

    public ResponseEntity<?> sender(Message messages){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("elbeknurmatov01@gmail.com");
        message.setTo(messages.getToEmail());
        message.setSubject(messages.getContact());
        message.setText(messages.getMessage());
        emailSender.send(message);
        return ResponseEntity.ok(new ResponseDto(200, "ok", null));

    }
}
