package com.example.demo.dto;

import lombok.Data;

@Data
public class EmployeeProfileDto {

    private Long id;
    private String name;
    private String email;
    private String team;
    private boolean active;
}
