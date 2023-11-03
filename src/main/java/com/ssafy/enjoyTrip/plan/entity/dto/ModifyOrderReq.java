package com.ssafy.enjoyTrip.plan.entity.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class ModifyOrderReq {
    private int planId;
    private String orderString;
}
