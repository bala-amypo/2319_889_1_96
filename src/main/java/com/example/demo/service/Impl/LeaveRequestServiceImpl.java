package com.example.demo.service.Impl;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService {

    @Override
    public LeaveRequestDto create(LeaveRequestDto dto) {
        return dto;
    }

    @Override
    public void approve(Long id) {}

    @Override
    public void reject(Long id) {}

    @Override
    public List<LeaveRequestDto> getByEmployee(Long empId) {
        return List.of();
    }

    @Override
    public List<LeaveRequestDto> getOverlappingForTeam(
            String team, LocalDate start, LocalDate end) {
        return List.of();
    }
}
