package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "team_capacity_config")
public class TeamCapacityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamName;

    private int minRequiredEmployees;

    // Constructors
    public TeamCapacityConfig() {
    }

    public TeamCapacityConfig(String teamName, int minRequiredEmployees) {
        this.teamName = teamName;
        this.minRequiredEmployees = minRequiredEmployees;
    }

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getMinRequiredEmployees() {
        return minRequiredEmployees;
    }

    public void setMinRequiredEmployees(int minRequiredEmployees) {
        this.minRequiredEmployees = minRequiredEmployees;
    }
}
