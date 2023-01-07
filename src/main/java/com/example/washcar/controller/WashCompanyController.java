package com.example.washcar.controller;

import com.example.washcar.dto.WashCompanyDto;
import com.example.washcar.mapper.WashCompanyMapper;
import com.example.washcar.service.WashCompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WashCompanyController {
    private final WashCompanyService washCompanyService;


    @PostMapping("/washCompanyInsert")
    public ResponseEntity<?> save(@RequestBody WashCompanyDto companyDto) {
        return washCompanyService.save(companyDto);
    }

    @PutMapping("/{washCompanyId}/addAvatar")
    public ResponseEntity<?> savePhoto(@RequestParam MultipartFile avatar, @PathVariable int washCompanyId) {
        return washCompanyService.savePhoto(avatar, washCompanyId);
    }

    @GetMapping("/{washCompanyId}/getAvatar")
    public ResponseEntity<?> getPhoto(@PathVariable int washCompanyId) {
        return washCompanyService.getPhoto(washCompanyId);
    }

    @GetMapping("/{washCompanyId}/analytics")
    public ResponseEntity<?> analytics(@PathVariable int washCompanyId, @RequestParam String dateFrom, @RequestParam String dateTo) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date from = sdf.parse(dateFrom);
        Date to = sdf.parse(dateTo);
        return washCompanyService.analytics(washCompanyId, from, to);
    }
}
