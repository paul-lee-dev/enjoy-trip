package com.ssafy.enjoyTrip.plan.service.entity.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TestDto1 {
    private int planId;
    private int userId;
    private String title;
    private List<PlanListDto> planlists;
}
