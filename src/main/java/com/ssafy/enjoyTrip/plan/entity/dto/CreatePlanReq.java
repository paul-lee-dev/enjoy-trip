package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.common.constant.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePlanReq {
    private int userId;
    private String title;
    private Scope scope;
}
