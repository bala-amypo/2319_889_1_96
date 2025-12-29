package com.example.demo.service;

import com.example.demo.model.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig updateRule(Long id, TeamCapacityConfig config);
}
