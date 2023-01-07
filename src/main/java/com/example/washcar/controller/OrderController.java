package com.example.washcar.controller;

import com.example.washcar.dto.OrderDto;
import com.example.washcar.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/{washCompanyId}/insertOrder")
    public ResponseEntity<?> save(@PathVariable int washCompanyId, @RequestBody OrderDto orderDto) {
        return orderService.save(orderDto, washCompanyId);
    }

    @GetMapping("/{washCompanyId}/orders")
    public ResponseEntity<?> get(
            @PathVariable int washCompanyId,
            @RequestParam boolean isActive,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dateFrom,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) String dateTo,
            @RequestParam(defaultValue = "1") int page) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date from = sdf.parse(dateFrom);
        Date to = sdf.parse(dateTo);
        return orderService.getData(isActive, from, to, page, washCompanyId);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<?> get(@PathVariable int id){
        return orderService.getById(id);
    }

    @PatchMapping("/{washCompanyId}/updateOrder")
    public ResponseEntity<?> update(@PathVariable String washCompanyId, @RequestBody OrderDto orderDto){
        return orderService.update(orderDto);
    }

}
