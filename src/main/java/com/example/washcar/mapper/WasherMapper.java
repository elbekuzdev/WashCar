package com.example.washcar.mapper;

import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.Washer;

import java.util.HashSet;
import java.util.Set;

public class WasherMapper {
    public static Washer toEntity(WasherDto washerDto){
        return new Washer(washerDto.getId(), washerDto.getName(), washerDto.getPhoneNumber(), washerDto.getStake(), null, washerDto.getIsActive());
    }

    public static Set<Washer> toEntity(Set<WasherDto> washerDtos){
        if (washerDtos != null){
            Set<Washer> washers = new HashSet<>();
            for (WasherDto washerDto: washerDtos){
                washers.add(toEntity(washerDto));
            }
            return washers;
        }
        return null;
    }
}
