package com.example.washcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WasherDto {
    private int id;
    private String name;
    private String phoneNumber;
    private int stake;
    private String image;
    private Boolean isActive = true;
}
