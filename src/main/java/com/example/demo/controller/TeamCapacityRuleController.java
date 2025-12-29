package com.example.demo.controller;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/capacity-rules")
@Tag(name = "Capacity Rules")
public class TeamCapacityRuleController {
    
    private final TeamCapacityRuleService capacityRuleService;

    public TeamCapacityRuleController(TeamCapacityRuleService capacityRuleService) {
        this.capacityRuleService = capacityRuleService;
    }

    @PostMapping
    @Operation(summary = "Create team capacity rule")
    public ResponseEntity<TeamCapacityConfig> create(@RequestBody TeamCapacityConfig rule) {
        TeamCapacityConfig created = capacityRuleService.createRule(rule);
        return ResponseEntity.ok(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update team capacity rule")
    public ResponseEntity<TeamCapacityConfig> update(@PathVariable Long id, @RequestBody TeamCapacityConfig rule) {
        TeamCapacityConfig updated = capacityRuleService.updateRule(id, rule);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/team/{teamName}")
    @Operation(summary = "Get capacity rule by team name")
    public ResponseEntity<TeamCapacityConfig> getByTeam(@PathVariable String teamName) {
        TeamCapacityConfig rule = capacityRuleService.getRuleByTeam(teamName);
        return ResponseEntity.ok(rule);
    }
}