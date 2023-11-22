package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.common.constant.Scope;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class CreatePlanReq {
    private Integer planId;
    private int userId;
    private String title;
    private Scope scope;

    public CreatePlanReq(CopyPlanReq copyPlanReq) {
        this.userId = copyPlanReq.getUserId();
        this.title = copyPlanReq.getTitle();
        this.scope = copyPlanReq.getScope();
    }
}
