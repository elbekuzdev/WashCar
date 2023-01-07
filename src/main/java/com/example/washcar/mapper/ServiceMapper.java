package com.example.washcar.mapper;

import com.example.washcar.dto.ServiceDto;
import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ServiceMapper {
    public static Service toEntity(ServiceDto serviceDto) {
        return new Service(serviceDto.getId(), serviceDto.getName(), serviceDto.getDuration(), serviceDto.getPrice(), null);
    }

    public static Set<Service> toEntity(Set<ServiceDto> washerDtos) {
        Set<Service> services = new HashSet<>();
        for (ServiceDto serviceDto : washerDtos) {
            services.add(toEntity(serviceDto));
        }
        return services;
    }

    public static ServiceDto toDto(Service service) {
        return new ServiceDto(service.getId(), service.getName(), service.getDuration(), service.getPrice());
    }

    public static Set<ServiceDto> toDto(Set<Service> services) {
        Set<ServiceDto> dtos = new HashSet<>();
        for (Service service : services) {
            dtos.add(toDto(service));
        }
        return dtos;
    }

    public static List<ServiceDto> toDto(List<Service> services){
        List<ServiceDto> dtos = new ArrayList<>(services.size());
        for (Service service: services)
            dtos.add(toDto(service));
        return dtos;
    }
}
