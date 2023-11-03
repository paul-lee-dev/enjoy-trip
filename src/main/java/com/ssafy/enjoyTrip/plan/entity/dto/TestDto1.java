package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.plan.entity.PlanList;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class TestDto1 {
    private int planId;
    private int userId;
    private String title;
    private List<PlanListDto> planlists;
}
