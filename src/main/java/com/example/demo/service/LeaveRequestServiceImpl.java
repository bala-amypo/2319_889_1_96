package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import com.example.demo.dto.LeaveRequestDto;

public interface LeaveRequestService {

    LeaveRequestDto create(LeaveRequestDto dto);

    void approve(Long id);

    void reject(Long id);

    List<LeaveRequestDto> getByEmployee(Long empId);

    List<LeaveRequestDto> getOverlappingForTeam(
            String team, LocalDate start, LocalDate end);
}
