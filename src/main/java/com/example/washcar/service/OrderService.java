package com.example.washcar.service;

import com.example.washcar.dto.OrderDto;
import com.example.washcar.dto.ResponseDto;
import com.example.washcar.entity.Order;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.mapper.OrderMapper;
import com.example.washcar.repo.OrderRepo;
import com.example.washcar.repo.ServiceRepo;
import com.example.washcar.repo.WashCompanyRepo;
import com.example.washcar.repo.WasherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;
    private final WashCompanyRepo washCompanyRepo;
    private final WasherRepo washerRepo;
    private final ServiceRepo serviceRepo;

    public ResponseEntity<?> save(OrderDto orderDto, int washCompanyId) {
        try {
            Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(washCompanyId);
            if (optionalWashCompany.isPresent()) {
                WashCompany washCompany = optionalWashCompany.get();
                Order order = OrderMapper.toEntity(orderDto);
                order.setWashCompany(washCompany);
                washerRepo.saveAll(order.getWashers());
                serviceRepo.saveAll(order.getServices());
                Order save = orderRepo.save(order);
                return ResponseEntity.ok(new ResponseDto(200, "saved", save));
            } else {
                return ResponseEntity.ok(new ResponseDto(200, "wash company not found", null));

            }


        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(new ResponseDto(200, "something went wrong", null));
        }
    }

    public ResponseEntity<?> getData(boolean isActive, Date from, Date to, int page, int companyId) {
        Pageable date = PageRequest.of(page - 1, 10, Sort.by("date"));
        return ResponseEntity.ok(new ResponseDto(200, "ok", OrderMapper.toDto(orderRepo.findByIsActiveAndDateBetweenAndWashCompanyId(isActive, from, to, companyId, date))));
    }

    public ResponseEntity<?> getById(int id) {
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return ResponseEntity.ok(new ResponseDto(200, "ok", OrderMapper.toDto(order)));
        } else {
            return ResponseEntity.ok(new ResponseDto(203, "order not found", null));
        }
    }

    public ResponseEntity<?> update(OrderDto orderDto) {
        Order order = OrderMapper.toEntity(orderDto);
        Optional<Order> optionalOrder = orderRepo.findById(order.getId());
        if (optionalOrder.isPresent()) {
            Order order1 = optionalOrder.get();
            concat(order, order1);
            serviceRepo.saveAll(order.getServices());
            washerRepo.saveAll(order.getWashers());
            Order save = orderRepo.save(order);
            return ResponseEntity.ok(new ResponseDto(200, "updated", save));
        } else {
            return ResponseEntity.ok(new ResponseDto(206, "order not found", null));
        }
    }

    private void concat(Order order, Order order1) {
        if (order.getCarModel() == null) {
            order.setCarModel(order1.getCarModel());
        }
        if (order.getServices() == null) {
            order.setServices(order1.getServices());
        }
        if (order.getWashers() == null) {
            order.setWashers(order1.getWashers());
        }
        if (order.getPrice() == null) {
            order.setPrice(order1.getPrice());
        }
        if (order.getCarNumber() == null) {
            order.setCarNumber(order1.getCarNumber());
        }
        if (order.getClientName() == null) {
            order.setClientName(order1.getClientName());
        }
        if (order.getClientNumber() == null) {
            order.setClientNumber(order1.getClientNumber());
        }
        if (order.getIsActive() == null) {
            order.setIsActive(order1.getIsActive());
        }
        if (order.getIsCancelled() == null) {
            order.setIsCancelled(order1.getIsCancelled());
        }
        if (order.getDate() == null) {
            order.setDate(order1.getDate());
        }
    }
}
