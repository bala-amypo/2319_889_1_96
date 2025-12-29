package com.example.demo.service.Impl;

import org.springframework.stereotype.Service;

import com.example.demo.model.TeamCapacityConfig;
import com.example.demo.service.TeamCapacityRuleService;

@Service
public class TeamCapacityRuleServiceImpl implements TeamCapacityRuleService {

    @Override
    public TeamCapacityConfig updateRule(Long id, TeamCapacityConfig config) {
        return config;
    }
}
