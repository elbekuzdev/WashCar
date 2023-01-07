package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.WashCompanyDto;
import com.example.washcar.entity.Order;
import com.example.washcar.entity.Photo;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.entity.Washer;
import com.example.washcar.mapper.WashCompanyMapper;
import com.example.washcar.repo.OrderRepo;
import com.example.washcar.repo.PhotoRepo;
import com.example.washcar.repo.WashCompanyRepo;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

@Service
@RequiredArgsConstructor
public class WashCompanyService {
    private final WashCompanyRepo washCompanyRepo;
    private final PhotoRepo photoRepo;
    private final OrderRepo orderRepo;


    public ResponseEntity<?> save(WashCompanyDto companyDto) {
        System.out.println(washCompanyRepo);
        WashCompany washCompany = WashCompanyMapper.toEntity(companyDto);
        System.out.println(washCompany);
        try {
            return ResponseEntity.ok(new ResponseDto(200, "saved", washCompanyRepo.save(washCompany)));
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto(205, "something went wrong", null));
        }
    }

    public ResponseEntity<?> savePhoto(MultipartFile multipartFile, int washCompanyId) {
        try {
            Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(washCompanyId);
            if (optionalWashCompany.isPresent()) {
                String type = Objects.requireNonNull(multipartFile.getContentType()).split("/")[1];
                String path = "\\src\\main\\resources\\static\\images\\company\\companyImage" + washCompanyId + "." + type;
                String currentDir = System.getProperty("user.dir") + path;
                WashCompany washCompany = optionalWashCompany.get();
                Photo photo = new Photo(0, path, multipartFile.getContentType());
                if (washCompany.getAvatar() != null) {
                    photo.setId(washCompany.getAvatar().getId());
                }
                photo = photoRepo.save(photo);
                washCompany.setAvatar(photo);
                washCompanyRepo.save(washCompany);
                File file = new File(currentDir);
                try {
                    multipartFile.transferTo(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return ResponseEntity.ok(new ResponseDto(200, "ok", null));
            } else {
                return ResponseEntity.ok(new ResponseDto(209, "company not found", null));
            }
        } catch (Exception e) {
            return ResponseEntity.ok(new ResponseDto(209, "not saved", null));
        }
    }

    public ResponseEntity<?> getPhoto(int companyId) {
        Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(companyId);
        if (optionalWashCompany.isPresent()) {
            WashCompany washCompany = optionalWashCompany.get();
            if (washCompany.getAvatar() != null) {
                Photo avatar = washCompany.getAvatar();
                String path = "\\src\\main\\resources\\static\\images\\company\\companyImage" + companyId + "." + avatar.getContentType().split("/")[1];
                String currentDir = System.getProperty("user.dir") + path;
                try {
                    ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(currentDir)));
                    return ResponseEntity.ok().contentType(MediaType.valueOf(avatar.getContentType().toUpperCase())).body(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.ok(new ResponseDto(209, "photo is invalid", null));
                }
            } else {
                return ResponseEntity.ok(new ResponseDto(209, "photo not saved", null));
            }
        } else {
            return ResponseEntity.ok(new ResponseDto(209, "company not found", null));
        }
    }

    public ResponseEntity<?> analytics(int washCompanyId, Date dateFrom, Date dateTo){
        HashMap<String, Object> data = new HashMap<>();
        System.out.println(dateFrom);
        System.out.println(dateTo);
        List<Order> orders = orderRepo.findByIsActiveAndDateBetweenAndWashCompanyId(true, dateFrom, dateTo, washCompanyId);
        System.out.println(orders);
        data.put("totalOrders", orders.size());
        HashSet<Washer> washers = new HashSet<>();
        double ordersSum = 0;
        double washersSum = 0;
        for (Order order: orders){
            ordersSum += order.getPrice();
            washersSum += order.getPrice() * 0.01;
            washers.addAll(order.getWashers());
        }
        data.put("totalWashers", washers.size());
        data.put("ordersSum", ordersSum);
        data.put("washersSum", washersSum);
        return ResponseEntity.ok(new ResponseDto(200, "ok", data));

    }
}
