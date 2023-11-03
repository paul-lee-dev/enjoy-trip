package com.ssafy.enjoyTrip.plan.service.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePlanListReq {
    private int planId;
    private int attractionId;
}
