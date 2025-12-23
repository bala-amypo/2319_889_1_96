package com.example.demo.service;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.Employee;
import com.example.demo.model.TeamCapacityRule;
import com.example.demo.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CapacityAnalysisService {

    private final EmployeeRepository employeeRepo;
    private final LeaveRepository leaveRepo;
    private final TeamCapacityRuleRepository ruleRepo;

    public CapacityAnalysisService(EmployeeRepository e, LeaveRepository l, TeamCapacityRuleRepository r) {
        this.employeeRepo = e;
        this.leaveRepo = l;
        this.ruleRepo = r;
    }

    public CapacityAnalysisResultDto analyze(String team, LocalDate date) {
        int total = (int) employeeRepo.findAll()
                .stream().filter(e -> team.equals(e.getTeam())).count();

        int onLeave = (int) leaveRepo
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqual(date, date)
                .stream().filter(l -> team.equals(l.getEmployee().getTeam())).count();

        int available = total - onLeave;

        TeamCapacityRule rule = ruleRepo.findAll().stream()
                .filter(r -> team.equals(r.getTeam())).findFirst().orElseThrow();

        return new CapacityAnalysisResultDto(
                team,
                available,
                available < rule.getMinimumRequired()
        );
    }
}
