package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.repository.TeamCapacityConfigRepository;
import com.example.demo.service.TeamCapacityRuleService;
import org.springframework.stereotype.Service;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {
    
    private final TeamCapacityConfigRepository capacityRepository;

    public TeamCapacityRuleServiceImpl(TeamCapacityConfigRepository capacityRepository) {
        this.capacityRepository = capacityRepository;
    }

    @Override
    public TeamCapacityConfig createRule(TeamCapacityConfig rule) {
        validateRule(rule);
        return capacityRepository.save(rule);
    }

    @Override
    public TeamCapacityConfig updateRule(Long id, TeamCapacityConfig updatedRule) {
        TeamCapacityConfig existing = capacityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity rule not found"));
        
        validateRule(updatedRule);
        
        existing.setTeamName(updatedRule.getTeamName());
        existing.setTotalHeadcount(updatedRule.getTotalHeadcount());
        existing.setMinCapacityPercent(updatedRule.getMinCapacityPercent());
        
        return capacityRepository.save(existing);
    }

    @Override
    public TeamCapacityConfig getRuleByTeam(String teamName) {
        return capacityRepository.findByTeamName(teamName)
                .orElseThrow(() -> new ResourceNotFoundException("Capacity config not found"));
    }

    private void validateRule(TeamCapacityConfig rule) {
        if (rule.getTotalHeadcount() == null || rule.getTotalHeadcount() <= 0) {
            throw new BadRequestException("Invalid total headcount");
        }
        if (rule.getMinCapacityPercent() == null || rule.getMinCapacityPercent() < 1 || rule.getMinCapacityPercent() > 100) {
            throw new BadRequestException("Min capacity percent must be between 1 and 100");
        }
    }
}