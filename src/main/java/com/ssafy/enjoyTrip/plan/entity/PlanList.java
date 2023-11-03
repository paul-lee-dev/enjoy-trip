package com.ssafy.enjoyTrip.plan.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class PlanList {
    private int planListId;
    private int planId;
    private int attractionId;
}
