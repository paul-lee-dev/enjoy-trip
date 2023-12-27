package com.ssafy.enjoyTrip.plan.entity.dto;

import com.ssafy.enjoyTrip.common.constant.Scope;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@Builder
public class GetPlansRes {
    private int planId;
    private int userId;
    private String title;
}
