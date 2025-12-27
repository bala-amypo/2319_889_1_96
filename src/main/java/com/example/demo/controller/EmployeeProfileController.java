package com.example.demo.controller;

import com.example.demo.dto.EmployeeProfileDto;
import com.example.demo.service.EmployeeProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@Tag(name = "Employee Profiles")
public class EmployeeProfileController {
    
    private final EmployeeProfileService employeeService;

    public EmployeeProfileController(EmployeeProfileService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @Operation(summary = "Create new employee profile")
    public ResponseEntity<EmployeeProfileDto> create(@RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto created = employeeService.create(dto);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update employee profile")
    public ResponseEntity<EmployeeProfileDto> update(@PathVariable Long id, @RequestBody EmployeeProfileDto dto) {
        EmployeeProfileDto updated = employeeService.update(id, dto);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get employee by ID")
    public ResponseEntity<EmployeeProfileDto> getById(@PathVariable Long id) {
        EmployeeProfileDto employee = employeeService.getById(id);
        return ResponseEntity.ok(employee);
    }

    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get active employees by team")
    public ResponseEntity<List<EmployeeProfileDto>> getByTeam(@PathVariable String teamName) {
        List<EmployeeProfileDto> employees = employeeService.getByTeam(teamName);
        return ResponseEntity.ok(employees);
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    public ResponseEntity<List<EmployeeProfileDto>> getAll() {
        List<EmployeeProfileDto> employees = employeeService.getAll();
        return ResponseEntity.ok(employees);
    }
}