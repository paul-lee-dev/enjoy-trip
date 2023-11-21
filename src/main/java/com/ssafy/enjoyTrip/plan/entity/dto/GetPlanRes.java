package com.ssafy.enjoyTrip.plan.entity.dto;

import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class GetPlanRes {
    private int planId;
    private int userId;
    private String title;
    private String orderString;
    private List<List<PlanListDto>> planLists;

    private Integer pageNo;

    /**
     * 계획 상세보기 -> 목록보기로 돌아갈 때 페이지 유지를 위한 정보 업데이트
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPlanLists(List<List<PlanListDto>> planLists) {
        this.planLists = planLists;
    }
}
