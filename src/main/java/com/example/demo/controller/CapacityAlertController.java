package com.example.demo.controller;

import com.example.demo.dto.CapacityAnalysisResultDto;
import com.example.demo.model.CapacityAlert;
import com.example.demo.repository.CapacityAlertRepository;
import com.example.demo.service.CapacityAnalysisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/capacity-alerts")
@Tag(name = "Capacity Alerts")
public class CapacityAlertController {
    
    private final CapacityAnalysisService capacityAnalysisService;
    private final CapacityAlertRepository alertRepository;

    public CapacityAlertController(CapacityAnalysisService capacityAnalysisService, CapacityAlertRepository alertRepository) {
        this.capacityAnalysisService = capacityAnalysisService;
        this.alertRepository = alertRepository;
    }

    @PostMapping("/analyze")
    @Operation(summary = "Analyze team capacity for date range")
    public ResponseEntity<CapacityAnalysisResultDto> analyze(@RequestBody Map<String, Object> request) {
        String teamName = (String) request.get("teamName");
        LocalDate start = LocalDate.parse((String) request.get("start"));
        LocalDate end = LocalDate.parse((String) request.get("end"));
        
        CapacityAnalysisResultDto result = capacityAnalysisService.analyzeTeamCapacity(teamName, start, end);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get capacity alerts for team")
    public ResponseEntity<List<CapacityAlert>> getAlertsForTeam(
            @PathVariable String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<CapacityAlert> alerts = alertRepository.findByTeamNameAndDateBetween(teamName, start, end);
        return ResponseEntity.ok(alerts);
    }
}