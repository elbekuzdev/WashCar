package com.example.washcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResDto {
    private int id;
    private Set<ServiceDto> services;
    private Set<WasherDto> washers;
    private Double price;
    private String carModel;
    private String carNumber;
    private String clientName;
    private String clientNumber;
    private Boolean isActive = true;
    private Boolean isCancelled = false;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
}
