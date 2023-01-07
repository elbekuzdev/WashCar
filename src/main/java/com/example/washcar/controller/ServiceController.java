package com.example.washcar.controller;

import com.example.washcar.dto.ServiceDto;
import com.example.washcar.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService service;

    @PostMapping("/{washCompanyId}/insertService")
    public ResponseEntity<?> save(@PathVariable int washCompanyId, @RequestBody ServiceDto serviceDto) {
        return service.save(serviceDto, washCompanyId);
    }

    @GetMapping("/{washCompanyId}/services")
    public ResponseEntity<?> getByName(@RequestParam String searchName, @RequestParam int page, @PathVariable int washCompanyId){
        return service.getByName(washCompanyId, searchName, page);
    }

    @GetMapping("/service")
    public ResponseEntity<?> getById(@RequestParam int id){
        return service.getById(id);
    }
    @PatchMapping("/{washCompanyId}/updateService")
    public ResponseEntity<?> update(@RequestBody ServiceDto serviceDto, @PathVariable int washCompanyId){
        return service.update(washCompanyId, serviceDto);
    }
}
