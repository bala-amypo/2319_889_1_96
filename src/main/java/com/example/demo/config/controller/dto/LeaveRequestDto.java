package com.example.demo.dto;

import lombok.Data;

@Data
public class LeaveRequestDto {
    private Long employeeId;
    private String startDate;
    private String endDate;
}
