package com.example.washcar.mapper;

import com.example.washcar.dto.OrderDto;
import com.example.washcar.entity.Order;
import com.example.washcar.entity.Washer;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {
    public static Order toEntity(OrderDto orderDto) {
        return new Order(orderDto.getId(), ServiceMapper.toEntity(orderDto.getServices()), WasherMapper.toEntity(orderDto.getWashers()), orderDto.getPrice(), orderDto.getCarModel(), orderDto.getCarNumber(), orderDto.getClientName(), orderDto.getClientNumber(), orderDto.getIsActive(), orderDto.getIsCancelled(), orderDto.getDate(), null);
    }

    public static OrderDto toDto(Order order) {
        return new OrderDto(order.getId(), ServiceMapper.toDto(order.getServices()), WasherMapper.toDto(order.getWashers()), order.getPrice(), order.getCarModel(), order.getCarNumber(), order.getClientName(), order.getClientNumber(), order.getIsActive(), order.getIsCancelled(), order.getDate());
    }

    public static List<OrderDto> toDto(List<Order> orders) {
        List<OrderDto> dtos = new ArrayList<>(orders.size());
        for (Order order : orders)
            dtos.add(toDto(order));
        return dtos;
    }
}
