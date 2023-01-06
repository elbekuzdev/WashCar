package com.example.washcar.mapper;

import com.example.washcar.dto.OrderDto;
import com.example.washcar.entity.Order;

public class OrderMapper {
    public static Order toEntity(OrderDto orderDto){
        return new Order(orderDto.getId(), ServiceMapper.toEntity(orderDto.getServices()), WasherMapper.toEntity(orderDto.getWashers()), orderDto.getPrice(), orderDto.getCarModel(), orderDto.getCarNumber(), orderDto.getClientName(), orderDto.getClientNumber(), orderDto.getIsActive(), orderDto.getIsCancelled(), orderDto.getDate(), null);
    }
}
