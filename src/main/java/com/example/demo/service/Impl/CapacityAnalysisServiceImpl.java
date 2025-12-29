package com.example.demo.service.impl;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.CapacityAlert;
import com.example.demo.model.EmployeeProfile;
import com.example.demo.model.LeaveRequest;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.repository.EmployeeProfileRepository;
import com.example.demo.repository.LeaveRequestRepository;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.CapacityAnalysisService;
import com.example.demo.util.DateRangeUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CapacityAnalysisServiceImpl implements CapacityAnalysisService {
    
    private final TeamCapacityConfigRepository capacityRepository;
    private final EmployeeProfileRepository employeeRepository;
    private final LeaveRequestRepository leaveRepository;
    private final CapacityAlertRepository alertRepository;

    public CapacityAnalysisServiceImpl(TeamCapacityConfigRepository capacityRepository,
                                     EmployeeProfileRepository employeeRepository,
                                     LeaveRequestRepository leaveRepository,
                                     CapacityAlertRepository alertRepository) {
        this.capacityRepository = capacityRepository;
        this.employeeRepository = employeeRepository;
        this.leaveRepository = leaveRepository;
        this.alertRepository = alertRepository;
    }

    @Override
    public CapacityAnalysisResultDto analyzeTeamCapacity(String teamName, LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new BadRequestException("Start date must not be after end date");
        }
        
        TeamCapacityConfig config = capacityRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));
        
        if (config.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }
        
        List<LocalDate> dates = DateRangeUtil.daysBetween(start, end);
        List<LeaveRequest> overlappingLeaves = leaveRepository.findApprovedOverlappingForTeam(teamName, start, end);
        
        Map<LocalDate, Double> capacityByDate = new HashMap<>();
        boolean risky = false;
        
        for (LocalDate date : dates) {
            long leavesOnDate = overlappingLeaves.stream()
                    .filter(leave -> !date.isBefore(leave.getStartDate()) && !date.isAfter(leave.getEndDate()))
                    .count();
            
            double capacity = ((config.getTotalHeadcount() - leavesOnDate) * 100.0) / config.getTotalHeadcount();
            capacityByDate.put(date, capacity);
            
            if (capacity < config.getMinCapacityPercent()) {
                risky = true;
                CapacityAlert alert = new CapacityAlert(teamName, date, "HIGH", 
                        "Team capacity below threshold: " + capacity + "%");
                alertRepository.save(alert);
            }
        }
        
        return new CapacityAnalysisResultDto(risky, capacityByDate);
    }
}