package com.ssafy.enjoyTrip.plan.entity.dto;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ModifyOrderReq {
    private int planId;
    @ApiParam(value="방문할 관광지(planlist) ID를 순서대로 합친 문자열", example = "12 32 51 19 1")
    private String orderString;
}
