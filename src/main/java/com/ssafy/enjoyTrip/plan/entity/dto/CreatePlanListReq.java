package com.ssafy.enjoyTrip.plan.entity.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePlanListReq {
    @ApiParam(value="관광지를 추가할 계획의 ID")
    private int planId;
    @ApiParam(value="추가할 관광지 ID")
    private int attractionId;
}
