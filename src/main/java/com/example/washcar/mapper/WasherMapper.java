package com.example.washcar.mapper;

import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.Washer;
import org.hibernate.loader.custom.NonUniqueDiscoveredSqlAliasException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WasherMapper {
    public static Washer toEntity(WasherDto washerDto) {
        return new Washer(washerDto.getId(), washerDto.getName(), washerDto.getPhoneNumber(), washerDto.getStake(), null, washerDto.getIsActive(), null);
    }

    public static Set<Washer> toEntity(Set<WasherDto> washerDtos) {
        if (washerDtos != null) {
            Set<Washer> washers = new HashSet<>();
            for (WasherDto washerDto : washerDtos) {
                washers.add(toEntity(washerDto));
            }
            return washers;
        }
        return null;
    }

    public static WasherDto toDto(Washer washer) {

        return new WasherDto(washer.getId(), washer.getName(), washer.getPhoneNumber(), washer.getStake(), String.format("http://localhost:8080/%d/getAvatar", washer.getId()), washer.getIsActive());
    }

    public static Set<WasherDto> toDto(Set<Washer> washers) {
        Set<WasherDto> washerDtos = new HashSet<>();
        for (Washer washer : washers) {
            washerDtos.add(toDto(washer));
        }
        return washerDtos;
    }

    public static List<WasherDto> toDto(List<Washer> washers) {
        List<WasherDto> washerDtos = new ArrayList<>(washers.size());
        for (Washer washer : washers)
            washerDtos.add(toDto(washer));
        return washerDtos;
    }
}
