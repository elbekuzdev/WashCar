package com.example.washcar.repo;

import com.example.washcar.entity.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findByIsActiveAndDateBetweenAndWashCompanyId(boolean isActive, Date from, Date to, int companyId, Pageable pageable);
}
