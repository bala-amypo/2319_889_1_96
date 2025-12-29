package com.example.demo.service;

import com.example.demo.model.TeamCapacityConfig;

public interface TeamCapacityRuleService {

    TeamCapacityConfig createRule(TeamCapacityConfig config);

    TeamCapacityConfig getRuleByTeam(String team);
}
