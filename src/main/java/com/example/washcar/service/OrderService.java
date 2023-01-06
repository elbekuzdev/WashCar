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

    public ResponseEntity<?> getById(int id){
        Optional<Order> optionalOrder = orderRepo.findById(id);
        if (optionalOrder.isPresent()){
            Order order = optionalOrder.get();
            return ResponseEntity.ok(new ResponseDto(200, "ok", OrderMapper.toDto(order)));
        }else{
            return ResponseEntity.ok(new ResponseDto(203, "order not found", null));
        }
    }
}
