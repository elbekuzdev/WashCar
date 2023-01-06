package com.example.washcar.dto;

import com.example.washcar.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashCompanyDto {
    private int id;
    private String name;
    protected MultipartFile avatar;
    private String location;
}
