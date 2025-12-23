package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.LeaveRequest;

import java.time.LocalDate;
import java.util.List;

public interface LeaveRepository extends JpaRepository<LeaveRequest, Long> {
    List<LeaveRequest> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(
        LocalDate end, LocalDate start
    );
}
