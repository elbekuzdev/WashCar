package com.example.washcar.controller;

import com.example.washcar.dto.WasherDto;
import com.example.washcar.service.WasherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WasherController {
    private final WasherService washerService;


    @PostMapping("/{washCompanyId}/insertWasher")
    public ResponseEntity<?> save(@PathVariable int washCompanyId, @RequestBody WasherDto washerDto) {
        return washerService.save(washerDto, washCompanyId);
    }

    @GetMapping("/{washCompanyId}/washers")
    public ResponseEntity<?> getByName(@RequestParam String searchName, @RequestParam int page, @PathVariable int washCompanyId) {
        return washerService.getByName(washCompanyId, searchName, page);
    }

    @PutMapping("/{washerId}/addPhoto")
    public ResponseEntity<?> savePhoto(@RequestParam MultipartFile photo, @PathVariable int washerId) {
        return washerService.savePhoto(washerId, photo);
    }

    @GetMapping("/{washerId}/getPhoto")
    public ResponseEntity<?> getPhoto(@PathVariable int washerId) {
        return washerService.getPhoto(washerId);
    }

    @GetMapping("/washer")
    public ResponseEntity<?> getById(@RequestParam int id) {
        return washerService.getById(id);
    }
}
