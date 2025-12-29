package com.example.demo.controller;

import com.example.demo.dto.LeaveRequestDto;
import com.example.demo.service.LeaveRequestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@Tag(name = "Leave Requests")
public class LeaveRequestController {
    
    private final LeaveRequestService leaveService;

    public LeaveRequestController(LeaveRequestService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    @Operation(summary = "Submit new leave request")
    public ResponseEntity<LeaveRequestDto> create(@RequestBody LeaveRequestDto dto) {
        LeaveRequestDto created = leaveService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}/approve")
    @Operation(summary = "Approve leave request")
    public ResponseEntity<LeaveRequestDto> approve(@PathVariable Long id) {
        LeaveRequestDto approved = leaveService.approve(id);
        return ResponseEntity.ok(approved);
    }

    @PutMapping("/{id}/reject")
    @Operation(summary = "Reject leave request")
    public ResponseEntity<LeaveRequestDto> reject(@PathVariable Long id) {
        LeaveRequestDto rejected = leaveService.reject(id);
        return ResponseEntity.ok(rejected);
    }

    @GetMapping("/employee/{employeeId}")
    @Operation(summary = "Get leaves by employee")
    public ResponseEntity<List<LeaveRequestDto>> getByEmployee(@PathVariable Long employeeId) {
        List<LeaveRequestDto> leaves = leaveService.getByEmployee(employeeId);
        return ResponseEntity.ok(leaves);
    }

    @GetMapping("/team-overlap")
    @Operation(summary = "Get overlapping approved leaves for team")
    public ResponseEntity<List<LeaveRequestDto>> getOverlappingForTeam(
            @RequestParam String teamName,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate start,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate end) {
        List<LeaveRequestDto> leaves = leaveService.getOverlappingForTeam(teamName, start, end);
        return ResponseEntity.ok(leaves);
    }
}