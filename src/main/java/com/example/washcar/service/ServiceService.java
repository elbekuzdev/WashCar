package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.ServiceDto;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.mapper.ServiceMapper;
import com.example.washcar.repo.ServiceRepo;
import com.example.washcar.repo.WashCompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ServiceService {
    private final ServiceRepo serviceRepo;
    private final WashCompanyRepo washCompanyRepo;

    public ResponseEntity<?> save(ServiceDto serviceDto, int washCompanyId){
        Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(washCompanyId);
        if (optionalWashCompany.isPresent()){
            WashCompany washCompany = optionalWashCompany.get();
            com.example.washcar.entity.Service service = ServiceMapper.toEntity(serviceDto);
            service.setWashCompany(washCompany);
            ServiceDto serviceDto1 = ServiceMapper.toDto(serviceRepo.save(service));
            return ResponseEntity.ok(new ResponseDto(200, "saved", serviceDto1));
        }else{
            return ResponseEntity.ok(new ResponseDto(205, "washCompany not found", null));
        }
    }

    public ResponseEntity<?> getByName(int washCompanyId, String name, int page){
        Pageable pageable = PageRequest.of(page - 1, 10);
        return ResponseEntity.ok(new ResponseDto(200, "ok", ServiceMapper.toDto(serviceRepo.findByWashCompanyIdAndNameContaining(washCompanyId, name, pageable))));
    }

    public ResponseEntity<?> getById(int serviceId){
        Optional<com.example.washcar.entity.Service> optionalService = serviceRepo.findById(serviceId);
        if (optionalService.isPresent()){
            com.example.washcar.entity.Service service = optionalService.get();
            return ResponseEntity.ok(new ResponseDto(200, "ok", ServiceMapper.toDto(service)));
        }else{
            return ResponseEntity.ok(new ResponseDto(204, "service not found", null));
        }
    }


}

