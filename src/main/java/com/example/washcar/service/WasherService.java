package com.example.washcar.service;

import com.example.washcar.dto.ResponseDto;
import com.example.washcar.dto.WasherDto;
import com.example.washcar.entity.Photo;
import com.example.washcar.entity.WashCompany;
import com.example.washcar.entity.Washer;
import com.example.washcar.mapper.WasherMapper;
import com.example.washcar.repo.PhotoRepo;
import com.example.washcar.repo.WashCompanyRepo;
import com.example.washcar.repo.WasherRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WasherService {

    private final WasherRepo washerRepo;
    private final WashCompanyRepo washCompanyRepo;
    private final PhotoRepo photoRepo;

    public ResponseEntity<?> save(WasherDto washerDto, int washCompanyId) {
        Optional<WashCompany> optionalWashCompany = washCompanyRepo.findById(washCompanyId);
        if (optionalWashCompany.isPresent()) {
            WashCompany washCompany = optionalWashCompany.get();
            Washer washer = WasherMapper.toEntity(washerDto);
            if (!washerRepo.existsByPhoneNumber(washer.getPhoneNumber())) {
                washer.setWashCompany(washCompany);
                WasherDto save = WasherMapper.toDto(washerRepo.save(washer));
                return ResponseEntity.ok(new ResponseDto(200, "saved", save));
            } else {
                return ResponseEntity.ok(new ResponseDto(205, "phone number already used", null));

            }
        } else {
            return ResponseEntity.ok(new ResponseDto(205, "company not found", null));
        }
    }

    public ResponseEntity<?> getByName(int washCompanyId, String name, int page) {
        Pageable of = PageRequest.of(page - 1, 10);
        return ResponseEntity.ok(new ResponseDto(200, "ok", washerRepo.findByWashCompanyIdAndNameContaining(washCompanyId, name, of)));
    }

    public ResponseEntity<?> savePhoto(int washerId, MultipartFile file) {
        Optional<Washer> optionalWasher = washerRepo.findById(washerId);
        if (optionalWasher.isPresent()) {
            Washer washer = optionalWasher.get();
            String type = Objects.requireNonNull(file.getContentType()).split("/")[1];
            String path = "\\src\\main\\resources\\static\\images\\washer\\washerImage" + washerId + "." + type;
            String currentDir = System.getProperty("user.dir") + path;
            Photo photo = new Photo(0, path, file.getContentType());
            if (washer.getImage() != null) {
                photo.setId(washer.getImage().getId());
            }
            photo = photoRepo.save(photo);
            washer.setImage(photo);
            washerRepo.save(washer);
            File file1 = new File(currentDir);
            try {
                file.transferTo(file1);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(new ResponseDto(200, "ok", null));
        } else {
            return ResponseEntity.ok(new ResponseDto(205, "washer not found", null));
        }
    }

    public ResponseEntity<?> getPhoto(int washerId) {
        Optional<Washer> optionalWasher = washerRepo.findById(washerId);
        if (optionalWasher.isPresent()) {
            Washer washer = optionalWasher.get();
            if (washer.getImage() != null) {
                Photo image = washer.getImage();
                String path = "\\src\\main\\resources\\static\\images\\washer\\washerImage" + washerId + "." + image.getContentType().split("/")[1];
                String currentDir = System.getProperty("user.dir") + path;
                try {
                    ByteArrayResource inputStream = new ByteArrayResource(Files.readAllBytes(Paths.get(currentDir)));
                    return ResponseEntity.ok().contentType(MediaType.valueOf(image.getContentType().toUpperCase())).body(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                    return ResponseEntity.ok(new ResponseDto(209, "photo is invalid", null));
                }
            } else {
                return ResponseEntity.ok(new ResponseDto(207, "photo not found", null));
            }
        } else {
            return ResponseEntity.ok(new ResponseDto(207, "washer not found", null));
        }
    }

    public ResponseEntity<?> getById(int washerId){
        Optional<Washer> optionalWasher = washerRepo.findById(washerId);
        if (optionalWasher.isPresent()){
            Washer washer = optionalWasher.get();
            if (washer.getImage() != null){
                WasherDto washerDto = WasherMapper.toDto(washer);
                washerDto.setImage(String.format("http://localhost:8080/%d/getPhoto", washer.getId()));
                return ResponseEntity.ok(new ResponseDto(200, "ok", washerDto));
            }else{
                WasherDto washerDto = WasherMapper.toDto(washer);
                washerDto.setImage("picture not found");
                return ResponseEntity.ok(new ResponseDto(200, "ok", washerDto));
            }

        }else{
            return ResponseEntity.ok(new ResponseDto(204, "washer not found", null));
        }
    }

}
