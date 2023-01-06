package com.example.washcar.repo;

import com.example.washcar.entity.WashCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WashCompanyRepo extends JpaRepository<WashCompany, Integer> {
}
