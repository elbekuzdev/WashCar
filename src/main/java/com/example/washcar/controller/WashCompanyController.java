package com.example.washcar.controller;

import com.example.washcar.dto.WashCompanyDto;
import com.example.washcar.mapper.WashCompanyMapper;
import com.example.washcar.service.WashCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WashCompanyController {
    private final WashCompanyService washCompanyService;


    @PostMapping("/washCompanyInsert")
    public ResponseEntity<?> save(@RequestBody WashCompanyDto companyDto) {
        return washCompanyService.save(companyDto);
    }
}
