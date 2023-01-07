package com.example.washcar.controller;

import com.example.washcar.dto.WasherDto;
import com.example.washcar.service.WasherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class WasherController {
    private final WasherService washerService;


    @PostMapping("/{washCompanyId}/insertWasher")
    public ResponseEntity<?> save(@PathVariable int washCompanyId, @RequestBody WasherDto washerDto){
        return washerService.save(washerDto, washCompanyId);
    }
}
