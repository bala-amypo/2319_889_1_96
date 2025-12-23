package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CapacityAnalysisResultDto {
    private String team;
    private int availableEmployees;
    private boolean belowThreshold;
}
