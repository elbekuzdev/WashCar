package com.example.washcar.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WashCompanyDto {
    private int id;
    private String name;
    protected String avatar;
    private String location;
}
