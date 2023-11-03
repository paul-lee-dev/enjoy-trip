package com.ssafy.enjoyTrip.plan.service.entity;

import com.ssafy.enjoyTrip.common.constant.Scope;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class Plan {
    private int planId;
    private int userId;
    private Scope scope;
    private String title;
    private String orderString;
}
