package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.service.CapacityAnalysisService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/capacity")
public class CapacityController {

    private final CapacityAnalysisService service;

    public CapacityController(CapacityAnalysisService service) {
        this.service = service;
    }

    @GetMapping("/{team}")
    public CapacityAnalysisResultDto analyze(@PathVariable String team) {
        return service.analyze(team, LocalDate.now());
    }
}
