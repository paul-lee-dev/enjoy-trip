package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.common.constant.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class CopyPlanReq {
    private int userId;
    private int targetPlanId;
    private Integer copyPlanId;
    private String title;
    private Scope scope;
    private List<PlanListDto> planLists;
}
