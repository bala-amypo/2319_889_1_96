package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    public LeaveRequest applyLeave(Long employeeId, LocalDate start, LocalDate end) {
        Employee emp = employeeRepository.findById(employeeId).orElseThrow();
        LeaveRequest leave = new LeaveRequest();
        leave.setEmployee(emp);
        leave.setStartDate(start);
        leave.setEndDate(end);
        return leaveRepository.save(leave);
    }
}
