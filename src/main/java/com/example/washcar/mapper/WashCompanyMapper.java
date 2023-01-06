package com.example.washcar.mapper;

import com.example.washcar.dto.WashCompanyDto;
import com.example.washcar.entity.Photo;
import com.example.washcar.entity.WashCompany;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class WashCompanyMapper {
    public static WashCompany toEntity(WashCompanyDto companyDto){
        return new WashCompany(companyDto.getId(), companyDto.getName(), null, companyDto.getLocation());
    }


}
