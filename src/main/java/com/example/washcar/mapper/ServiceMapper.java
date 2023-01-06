package com.example.washcar.mapper;

import com.example.washcar.dto.ServiceDto;
import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.Service;

import java.util.HashSet;
import java.util.Set;

public class ServiceMapper {
    public static Service toEntity(ServiceDto serviceDto){
        return new Service(serviceDto.getId(), serviceDto.getName(), serviceDto.getDuration(), serviceDto.getPrice());
    }

    public static Set<Service> toEntity(Set<ServiceDto> washerDtos){
        Set<Service> services = new HashSet<>();
        for (ServiceDto serviceDto: washerDtos){
            services.add(toEntity(serviceDto));
        }
        return services;
    }
}
