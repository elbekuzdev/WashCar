package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.entity.Washer;
import com.example.washcar.mapper.WasherMapper;
import com.example.washcar.repo.WashCompanyRepo;
import com.example.washcar.repo.WasherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasherService {

    private final WasherRepo washerRepo;
    private final WashCompanyRepo washCompanyRepo;

    public ResponseEntity<?> save(WasherDto washerDto, int washCompanyId){
        Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(washCompanyId);
        if (optionalWashCompany.isPresent()){
            WashCompany washCompany = optionalWashCompany.get();
            Washer washer = WasherMapper.toEntity(washerDto);
            if (!washerRepo.existsByPhoneNumber(washer.getPhoneNumber())){
                washer.setWashCompany(washCompany);
                WasherDto save = WasherMapper.toDto(washerRepo.save(washer));
                return ResponseEntity.ok(new ResponseDto(200, "saved", save));
            }else{
                return ResponseEntity.ok(new ResponseDto(205, "phone number already used", null));

            }
        }else{
            return ResponseEntity.ok(new ResponseDto(205, "company not found", null));
        }
    }

}
