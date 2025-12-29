package com.example.demo.service.Impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;

@Service
public class EmployeeProfileServiceImpl implements EmployeeProfileService {

    @Override
    public EmployeeProfileDto create(EmployeeProfileDto dto) {
        return dto;
    }

    @Override
    public EmployeeProfileDto update(Long id, EmployeeProfileDto dto) {
        return dto;
    }

    @Override
    public EmployeeProfileDto getById(Long id) {
        return new EmployeeProfileDto();
    }

    @Override
    public List<EmployeeProfileDto> getByTeam(String team) {
        return List.of();
    }

    @Override
    public List<EmployeeProfileDto> getAll() {
        return List.of();
    }
}
