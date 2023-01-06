package com.example.washcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDto {
    private int id;
    private String name;
    private int duration;
    private int price;
}
