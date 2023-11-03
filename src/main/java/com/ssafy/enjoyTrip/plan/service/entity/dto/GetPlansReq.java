package com.ssafy.enjoyTrip.plan.service.entity.dto;

import lombok.*;

import static com.ssafy.enjoyTrip.common.constant.Constants.PAGE_SIZE;

@Getter @Setter
public class GetPlansReq {
    private String keyword;
    private Integer pageNo;
    private Integer userId;
    private int start;
    private final int SIZE = PAGE_SIZE;

    @Builder
    public GetPlansReq(String keyword, Integer pageNo, Integer userId) {
        this.keyword = keyword;
        this.pageNo = pageNo;
        this.userId = userId;
        this.start = 0;
    }
}
