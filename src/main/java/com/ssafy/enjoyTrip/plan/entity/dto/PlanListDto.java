package com.ssafy.enjoyTrip.plan.entity.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class PlanListDto {
    private int planListId;
    private int planId;
    private int attractionId;
    private int dayNum;
}