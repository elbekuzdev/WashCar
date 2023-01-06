package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.WashCompanyDto;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.mapper.WashCompanyMapper;
import com.example.washcar.repo.WashCompanyRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WashCompanyService {
    private final WashCompanyRepo washCompanyRepo;


    public ResponseEntity<?> save(WashCompanyDto companyDto){
        System.out.println(washCompanyRepo);
        WashCompany washCompany = WashCompanyMapper.toEntity(companyDto);
        System.out.println(washCompany);
        try {
            return ResponseEntity.ok(new ResponseDto(200, "saved", washCompanyRepo.save(washCompany)));
        }catch (Exception e){
            return ResponseEntity.ok(new ResponseDto(205, "something went wrong", null));
        }
    }
}
