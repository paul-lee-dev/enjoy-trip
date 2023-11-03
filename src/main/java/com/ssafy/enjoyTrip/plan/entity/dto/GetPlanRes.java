package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.common.constant.Scope;
import com.ssafy.enjoyTrip.plan.entity.PlanList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@NoArgsConstructor
public class GetPlanRes {
    private int planId;
    private int userId;
    private String title;
    private String orderString;
    private List<PlanListDto> planlists;

    private Integer pageNo;

    /**
     * 계획 상세보기 -> 목록보기로 돌아갈 때 페이지 유지를 위한 정보 업데이트
     * @param pageNo
     */
    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public void setPlanlists(List<PlanListDto> planlists) {
        this.planlists = planlists;
    }
}
